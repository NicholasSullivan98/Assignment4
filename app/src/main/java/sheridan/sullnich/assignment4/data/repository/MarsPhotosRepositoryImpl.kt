/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sheridan.sullnich.assignment4.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sheridan.sullnich.assignment4.data.local.LocalMarsPhotos
import sheridan.sullnich.assignment4.data.local.MarsPhotoDao
import sheridan.sullnich.assignment4.data.remote.MarsPhotoApi
import sheridan.sullnich.assignment4.data.remote.RemoteMarsPhoto
import sheridan.sullnich.assignment4.domain.MarsPhoto
import javax.inject.Inject
import javax.inject.Singleton


@OptIn(DelicateCoroutinesApi::class)
@Singleton
class MarsPhotosRepositoryImpl(
    private val marsphotoApi: MarsPhotoApi,
    private val marsphotoDao: MarsPhotoDao,
    private val externalScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) : MarsPhotoRepository {

    @Inject
    constructor(
        marsphotoApi: MarsPhotoApi,
        marsphotoDao: MarsPhotoDao
    ) : this(marsphotoApi, marsphotoDao, GlobalScope, Dispatchers.IO)

    override fun getAllMarsPhotosFlow(): Flow<List<MarsPhoto>> =
        marsphotoDao.getAllMarsPhotoFlow().map { list -> list.map { it.toMarsPhoto() } }
            .flowOn(dispatcher)

    override suspend fun getMarsPhotoById(id: Int):MarsPhoto? = withContext(dispatcher) {
        marsphotoDao.getMarsPhotoById(id)?.toMarsPhoto()
    }

    override suspend fun refreshMarsPhotos() {
        externalScope.launch(dispatcher) {

            val marsPhotos: Deferred<List<LocalMarsPhotos>> = async {
                marsphotoApi.getAllMarsPhotos().map { it.toLocalMarsPhoto() }
            }

            marsphotoDao.refreshMarsPhotos(marsPhotos.await())
        }
    }

    override suspend fun clearDatabase() {
        externalScope.launch(dispatcher) {
            marsphotoDao.deleteAllMarsPhotos()
        }
    }
}

fun LocalMarsPhotos.toMarsPhoto(): MarsPhoto = MarsPhoto(
    id = id, imgSrc = imgSrc
)

fun RemoteMarsPhoto.toLocalMarsPhoto(): LocalMarsPhotos = LocalMarsPhotos(
    id = this.id, imgSrc = this.imgSrc
)







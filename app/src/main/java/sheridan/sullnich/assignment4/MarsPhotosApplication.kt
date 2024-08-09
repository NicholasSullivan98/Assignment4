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
package sheridan.sullnich.assignment4

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import sheridan.sullnich.assignment4.data.repository.MarsPhotoRepository
import javax.inject.Inject

@HiltAndroidApp
class MarsPhotosApplication() : Application(){

    @Inject
    lateinit var repository: MarsPhotoRepository

    override fun onCreate() {
        super.onCreate()

        MainScope().launch {
            repository.refreshMarsPhotos()
        }
    }
}

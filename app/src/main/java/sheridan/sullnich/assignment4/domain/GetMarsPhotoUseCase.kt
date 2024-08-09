package sheridan.sullnich.assignment4.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sheridan.sullnich.assignment4.data.repository.MarsPhotosRepositoryImpl
import javax.inject.Inject

class GetMarsPhotoUseCase @Inject constructor(
    private val repository: MarsPhotosRepositoryImpl
) {
    operator fun invoke(): Flow<List<MarsPhoto>> = repository.getAllMarsPhotosFlow().map { list ->
        list.sortedBy { it.id }
    }
}
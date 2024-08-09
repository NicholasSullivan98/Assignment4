package sheridan.sullnich.assignment4.domain

import sheridan.sullnich.assignment4.data.repository.MarsPhotosRepositoryImpl
import javax.inject.Inject

class ClearUseCase @Inject constructor(
    private val repository: MarsPhotosRepositoryImpl
) {
    suspend operator fun invoke() = repository.clearDatabase()
}
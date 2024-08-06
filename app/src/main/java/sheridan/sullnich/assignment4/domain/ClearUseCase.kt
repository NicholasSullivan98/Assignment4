package sheridan.sullnich.assignment4.domain

import sheridan.sullnich.assignment4.data.repository.MarsPhotosRepository
import javax.inject.Inject

class ClearUseCase @Inject constructor(
    private val repository: MarsPhotosRepository
) {
    suspend operator fun invoke() = repository.clearDatabase()
}
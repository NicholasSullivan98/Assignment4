package sheridan.sullnich.assignment4.domain

import sheridan.sullnich.assignment4.data.repository.MarsPhotoRepository
import javax.inject.Inject

class ReloadUseCase @Inject constructor(
    private val repository: MarsPhotoRepository
) {
    suspend operator fun invoke() = repository.refreshMarsPhotos()
}
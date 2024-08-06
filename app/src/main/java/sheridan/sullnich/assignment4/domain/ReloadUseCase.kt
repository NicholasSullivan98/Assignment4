package ca.tetervak.restaurantapp.domain

import sheridan.sullnich.assignment4.data.repository.PhotoRepository
import javax.inject.Inject

class ReloadUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke() = repository.refreshMarsPhotos()
}
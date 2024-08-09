package ca.tetervak.restaurantapp.domain

import sheridan.sullnich.assignment4.data.repository.MarsPhotoRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: MarsPhotoRepository
) {
    suspend operator fun invoke(id: Int, currentIsFavorite: Boolean) =
        repository.setIsFavoriteById(id, currentIsFavorite.not())
}
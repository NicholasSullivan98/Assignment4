package ca.tetervak.restaurantapp.domain

import sheridan.sullnich.assignment4.data.repository.PhotoRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(id: Int, currentIsFavorite: Boolean) =
        repository.setIsFavoriteById(id, currentIsFavorite.not())
}
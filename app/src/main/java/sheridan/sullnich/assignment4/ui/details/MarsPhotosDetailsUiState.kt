package sheridan.sullnich.assignment4.ui.details

import sheridan.sullnich.assignment4.domain.MarsPhoto

sealed interface DetailsUiState {
    object Loading : DetailsUiState
    data class Success(val marsPhoto: MarsPhoto) : DetailsUiState
}
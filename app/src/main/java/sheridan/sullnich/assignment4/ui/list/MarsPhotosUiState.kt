package sheridan.sullnich.assignment4.ui.list

import sheridan.sullnich.assignment4.domain.MarsPhoto

sealed interface MarsPhotosUiState {
    object Loading : MarsPhotosUiState
    data class Success(val marsPhoto: List<MarsPhoto>) : MarsPhotosUiState
}
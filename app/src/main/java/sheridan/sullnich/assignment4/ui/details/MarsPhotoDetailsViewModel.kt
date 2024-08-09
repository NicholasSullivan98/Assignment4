package sheridan.sullnich.assignment4.ui.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import sheridan.sullnich.assignment4.data.repository.MarsPhotoRepository
import sheridan.sullnich.assignment4.domain.MarsPhoto
import sheridan.sullnich.assignment4.ui.navigation.DetailsDestination
import javax.inject.Inject

@HiltViewModel
class MarsPhotoDetailsViewModel @Inject constructor(
    repository: MarsPhotoRepository, stateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailsUiState: MutableStateFlow<DetailsUiState> =
        MutableStateFlow(DetailsUiState.Loading)
    val detailsUiState: StateFlow<DetailsUiState> = _detailsUiState

    init {
        val id: Int = stateHandle.get<Int>(DetailsDestination.marsphotoIdArg)!!
        viewModelScope.launch {
            val marsPhoto: MarsPhoto? = repository.getMarsPhotoById(id)
            if(marsPhoto != null){
                _detailsUiState.update {
                    DetailsUiState.Success(marsPhoto = marsPhoto)
                }
            } else {
                Log.e("MarsPhotoDetailsViewModel", "data for id=$id is not found")
            }
        }
    }
}
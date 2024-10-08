package sheridan.sullnich.assignment4.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sheridan.sullnich.assignment4.domain.ReloadUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import sheridan.sullnich.assignment4.domain.ClearUseCase
import sheridan.sullnich.assignment4.domain.GetMarsPhotoUseCase
import javax.inject.Inject

@HiltViewModel
class MarsPhotosViewModel @Inject constructor(
    getMarsPhotoUseCase: GetMarsPhotoUseCase,
    private val reloadUseCase: ReloadUseCase,
    private val clearUseCase: ClearUseCase
) : ViewModel() {

    val marsPhotosUiState: StateFlow<MarsPhotosUiState> =
        getMarsPhotoUseCase().map { MarsPhotosUiState.Success(it) }.catch { errorHandler }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = MarsPhotosUiState.Loading
        )

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    fun reload() = viewModelScope.launch(errorHandler) {
        reloadUseCase()
    }

    fun clear() = viewModelScope.launch(errorHandler) {
        clearUseCase()
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
package sheridan.sullnich.assignment4.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import sheridan.sullnich.assignment4.domain.MarsPhoto
import sheridan.sullnich.assignment4.ui.common.MarsPhotoDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarsPhotosDetailsScreen(
    viewModel: MarsPhotoDetailsViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state: State<DetailsUiState> = viewModel.detailsUiState.collectAsState()
    val detailsUiState: DetailsUiState = state.value

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MarsPhotoDetailsTopBar(onBack, scrollBehavior) }
    ) { innerPadding ->
        if (detailsUiState is DetailsUiState.Success) {
            DetailsBody(
                marsPhoto = detailsUiState.marsPhoto,
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            )
        }
    }
}

@Composable
private fun DetailsBody(
    marsPhoto: MarsPhoto, modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        MarsPhotoDetails(
            id = marsPhoto.id,
            imgSrc = marsPhoto.imgSrc,
            modifier = Modifier.padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
    }
}
package sheridan.sullnich.assignment4.ui.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import sheridan.sullnich.assignment4.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MarsPhotosTopBar(
    onReload: () -> Unit,
    onClear: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) = CenterAlignedTopAppBar(
    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary,
    ),
    title = {
        Text(
            stringResource(R.string.nav_list_title),
            style = MaterialTheme.typography.headlineMedium
        )
    },
    actions = {
        IconButton(
            onClick = onClear,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = stringResource(R.string.clear)
            )
        }
        IconButton(
            onClick = onReload,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = stringResource(R.string.refresh)
            )
        }
    },
    scrollBehavior = scrollBehavior,
)
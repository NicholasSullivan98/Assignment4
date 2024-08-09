package sheridan.sullnich.assignment4.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MarsPhotoDetails(
    id: Int,
    imgSrc: String,
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
) {
    Column(
        horizontalAlignment = horizontalAlignment, modifier = modifier
    ) {
        Text(
            text = "Image ID: " + id.toString(), style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Image Src: " + imgSrc, style = MaterialTheme.typography.bodyLarge
        )
    }
}
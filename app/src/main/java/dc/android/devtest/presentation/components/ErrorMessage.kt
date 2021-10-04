package dc.android.devtest.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import dc.android.devtest.common.resolveError
import dc.android.devtest.presentation.city_list.CityListUiState

@Composable
fun ErrorMessage(
    modifier: Modifier = Modifier,
    error: Exception
) {
    val resources = LocalContext.current.resources

    Text(
        text = resolveError(
            e = error,
            resources = resources
        ),
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxSize()
    )
}
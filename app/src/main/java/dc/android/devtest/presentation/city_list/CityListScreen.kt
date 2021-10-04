package dc.android.devtest.presentation.city_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dc.android.devtest.R
import dc.android.devtest.presentation.city_list.components.CityCard
import dc.android.devtest.presentation.components.ErrorMessage

@ExperimentalComposeUiApi
@Composable
fun CityListScreen(
    viewModel: CityListViewModel = hiltViewModel(),
    navigateToWeatherDetails: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    var textFieldState by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    viewModel.loadCities()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                SearchComponent(
                    textFieldState = textFieldState,
                    onTextFieldChange = { newValue ->
                        textFieldState = newValue
                    },
                    onSearchButtonClicked = {
                        keyboardController?.hide()
                        viewModel.searchCities(textFieldState)
                    }
                )
            }
            when (uiState) {
                is CityListUiState.Error -> {
                    item {
                        ErrorMessage(
                            error = (uiState as CityListUiState.Error).exception
                        )
                    }
                }

                CityListUiState.Loading -> {
                    item {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                is CityListUiState.State -> {
                    val data = (uiState as CityListUiState.State).data
                    items(data) { city ->
                        CityCard(cityModel = city) { cityId ->
                            navigateToWeatherDetails(cityId)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun SearchComponent(
    textFieldState: String,
    onTextFieldChange: (String) -> Unit,
    onSearchButtonClicked: () -> Unit
) {
    OutlinedTextField(
        value = textFieldState,
        onValueChange = onTextFieldChange,
        label = { Text(text = stringResource(id = R.string.search_hint)) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearchButtonClicked() }
        )
    )
    Spacer(modifier = Modifier.height(8.dp))
    Button(onClick = onSearchButtonClicked) {
        Text(text = stringResource(id = R.string.search))
    }
    Spacer(modifier = Modifier.height(16.dp))
}
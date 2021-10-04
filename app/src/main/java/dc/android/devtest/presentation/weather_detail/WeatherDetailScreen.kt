package dc.android.devtest.presentation.weather_detail

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import dc.android.devtest.R
import dc.android.devtest.common.extension.formatToLocalizedDate
import dc.android.devtest.common.extension.getDrawableByName
import dc.android.devtest.presentation.components.ErrorMessage
import dc.android.devtest.presentation.ui.theme.darkPurple
import dc.android.devtest.presentation.ui.theme.lightPurple
import dc.android.devtest.presentation.weather_detail.model.WeatherDetailsModel
import java.util.*

@ExperimentalCoilApi
@Composable
fun WeatherDetailScreen(
    cityId: String,
    viewModel: WeatherDetailsViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    val currentLanguage = Locale.getDefault().language
    viewModel.getWeather(cityId = cityId, currentLanguage)

    Column(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is DetailsUiState.Error -> {
                ErrorMessage(
                    error = (uiState as DetailsUiState.Error).exception
                )
            }
            DetailsUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is DetailsUiState.State -> {
                val data = (uiState as DetailsUiState.State).data
                DetailTopBar(data)
                DetailContent(data)
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun DetailTopBar(data: WeatherDetailsModel) {
    val resources = LocalContext.current.resources
    Surface(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        color = MaterialTheme.colors.lightPurple
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = getLocaleDate(data.forecastDate, resources),
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.darkPurple
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = data.cityName,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.darkPurple
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(80.dp),
                    painter = rememberImagePainter(
                        data = resources.getDrawableByName(
                            context = LocalContext.current,
                            weatherIcon = data.weatherIcon
                        ),
                        builder = {
                            placeholder(R.drawable.ic_image)
                            error(R.drawable.ic_image)
                        }
                    ),
                    contentDescription = stringResource(id = R.string.weather_icon_description)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = data.temperature,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.darkPurple
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = data.weatherDescription,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.darkPurple
            )
        }
    }

}

@Composable
fun DetailContent(data: WeatherDetailsModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = data.timezone,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.darkPurple
        )
        Text(
            text = stringResource(id = R.string.min_temperature, data.minTemp),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.darkPurple
        )
        Text(
            text = stringResource(id = R.string.max_temperature, data.maxTemp),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.darkPurple
        )
        Text(
            text = stringResource(id = R.string.apparent_temperature, data.apparentTemp),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.darkPurple
        )
        Text(
            text = stringResource(id = R.string.humidity, data.humidity),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.darkPurple
        )
        Text(
            text = stringResource(id = R.string.wind_speed, data.wind),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.darkPurple
        )
        Text(
            text = stringResource(id = R.string.precipitation, data.precipitation),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.darkPurple
        )
    }
}

private fun getLocaleDate(forecastDate: Calendar, resources: Resources): String {
    return forecastDate.let { calendar ->
        val pattern = resources.getString(R.string.date_pattern)
        resources.getString(
            R.string.forecast_day,
            calendar.time.formatToLocalizedDate(pattern)
        )
    }
}


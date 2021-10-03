package dc.android.devtest.presentation.weather_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import coil.load
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dc.android.devtest.R
import dc.android.devtest.common.extension.formatToLocalizedDate
import dc.android.devtest.common.extension.getDrawableByName
import dc.android.devtest.common.resolveError
import dc.android.devtest.databinding.FragmentWeatherDetailsBinding
import dc.android.devtest.presentation.BindingFragment
import dc.android.devtest.presentation.weather_detail.model.WeatherDetailsModel
import kotlinx.coroutines.flow.collect
import java.util.*

@AndroidEntryPoint
class WeatherDetailsFragment : BindingFragment<FragmentWeatherDetailsBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentWeatherDetailsBinding::inflate

    private val viewModel: WeatherDetailsViewModel by viewModels()

    private val args: WeatherDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentLanguage = Locale.getDefault().language
        viewModel.getWeather(cityId = args.cityId, currentLanguage)

        setupObservables()
    }

    private fun setupObservables() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is DetailsUiState.Error -> {
                        binding.progressBar.root.visibility =
                            View.GONE

                        val errorMessage = resolveError(uiState.exception, resources)
                        Snackbar.make(
                            requireView(),
                            errorMessage,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    DetailsUiState.Loading -> binding.progressBar.root.visibility =
                        View.VISIBLE
                    is DetailsUiState.State -> {
                        binding.progressBar.root.visibility =
                            View.GONE
                        bindDataToUi(uiState.data)
                    }
                }
            }
        }
    }

    private fun bindDataToUi(data: WeatherDetailsModel) {
        with(binding) {
            tvCity.text = data.cityName
            tvForecastDate.text = getLocaleDate(data.forecastDate)
            tvTemperature.text = data.temperature
            tvWeatherDescription.text = data.weatherDescription
            tvTimezone.text = data.timezone
            tvMinTemp.text = resources.getString(R.string.min_temperature, data.minTemp)
            tvMaxTemp.text = resources.getString(R.string.max_temperature, data.maxTemp)
            tvApparentTemp.text =
                resources.getString(R.string.apparent_temperature, data.apparentTemp)
            tvHumidity.text = resources.getString(R.string.humidity, data.humidity)
            tvWind.text = resources.getString(R.string.wind_speed, data.wind)
            tvPrecipitation.text = resources.getString(R.string.precipitation, data.precipitation)
            ivWeatherIcon.load(resources.getDrawableByName(requireContext(), data.weatherIcon)) {
                crossfade(true)
                placeholder(R.drawable.ic_image)
            }
        }
    }

    private fun getLocaleDate(forecastDate: Calendar): String {
        return forecastDate.let { calendar ->
            val pattern = resources.getString(R.string.date_pattern)
            resources.getString(
                R.string.forecast_day,
                calendar.time.formatToLocalizedDate(pattern)
            )
        }
    }
}
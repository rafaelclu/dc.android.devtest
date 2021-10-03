package dc.android.devtest.presentation.weather_detail

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import dc.android.devtest.databinding.FragmentWeatherDetailsBinding
import dc.android.devtest.presentation.BindingFragment

@AndroidEntryPoint
class WeatherDetailsFragment : BindingFragment<FragmentWeatherDetailsBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentWeatherDetailsBinding::inflate


}
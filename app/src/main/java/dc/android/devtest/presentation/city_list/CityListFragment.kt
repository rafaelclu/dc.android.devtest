package dc.android.devtest.presentation.city_list

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import dc.android.devtest.databinding.FragmentCityListBinding
import dc.android.devtest.presentation.BindingFragment

@AndroidEntryPoint
class CityListFragment : BindingFragment<FragmentCityListBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentCityListBinding::inflate

}
package dc.android.devtest.presentation.city_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dc.android.devtest.common.extension.hideKeyboard
import dc.android.devtest.common.resolveError
import dc.android.devtest.databinding.FragmentCityListBinding
import dc.android.devtest.presentation.BindingFragment
import kotlinx.coroutines.flow.collect
import java.util.*

@AndroidEntryPoint
class CityListFragment : BindingFragment<FragmentCityListBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentCityListBinding::inflate

    private val viewModel: CityListViewModel by viewModels()

    private lateinit var cityAdapter: CityListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        initComponents()

        setupObservers()

        viewModel.loadCities()
    }

    private fun initAdapter() {
        cityAdapter = CityListAdapter { city ->
            navigateToDetailsScreen(city.cityId)
        }
    }

    private fun initComponents() {
        binding.rvCities.adapter = cityAdapter

        binding.btnSearch.setOnClickListener {
            requireContext().hideKeyboard(requireView())
            searchCities()
        }

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                requireContext().hideKeyboard(requireView())

                searchCities()

                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when(uiState) {
                    is CityListUiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(
                            requireView(),
                            resolveError(uiState.exception, resources),
                            BaseTransientBottomBar.LENGTH_SHORT
                        ).show()
                    }

                    CityListUiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is CityListUiState.State -> {
                        binding.progressBar.visibility = View.GONE
                        cityAdapter.setCities(uiState.data)
                    }
                }
            }
        }
    }

    private fun searchCities() {
        cityAdapter.clearData()

        viewModel.searchCities(
            cityName = binding.etSearch.text.toString()
        )
    }

    private fun navigateToDetailsScreen(id: String) {
        findNavController().navigate(
            CityListFragmentDirections
                .actionCityListFragmentToWeatherDetailsFragment(cityId = id)
        )
    }

}
package dc.android.devtest.presentation.city_list

import com.google.common.truth.Truth.assertThat
import dc.android.devtest.data.repository.FakeCityRepository
import dc.android.devtest.domain.use_case.GetCitiesUseCase
import dc.android.devtest.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test


class CityListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val fakeCityRepository = FakeCityRepository()
    private val getCitiesUseCase = GetCitiesUseCase(fakeCityRepository)

    @Test
    fun `Load cities use case returns error`() = runBlocking {
        val viewModel = CityListViewModel(getCitiesUseCase)
        fakeCityRepository.setShouldReturnNetworkError(true)

        viewModel.loadCities()
        val response = viewModel.uiState.first()

        assertThat(response is CityListUiState.Error).isTrue()
    }

    @Test
    fun `Search cities use case returns Success`() = runBlocking {
        val viewModel = CityListViewModel(getCitiesUseCase)

        viewModel.loadCities()
        viewModel.searchCities("Vi")
        val response = viewModel.uiState.first()

        assertThat(response is CityListUiState.State).isTrue()
    }
}
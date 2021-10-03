package dc.android.devtest.domain.use_case

import com.google.common.truth.Truth.assertThat
import dc.android.devtest.common.Resource
import dc.android.devtest.data.repository.FakeCityRepository
import dc.android.devtest.util.cityDummyList
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test


class GetCitiesUseCaseTest {

    private val fakeCityRepository = FakeCityRepository()

    @Test
    fun `Load cities use case returns Success`() = runBlocking {
        val getCitiesUseCase = GetCitiesUseCase(fakeCityRepository)

        val response = getCitiesUseCase()
        val resource = response.first()

        assertThat(resource is Resource.Success).isTrue()
    }

    @Test
    fun `Load cities use case returns expected value`() = runBlocking {
        val getCitiesUseCase = GetCitiesUseCase(fakeCityRepository)

        val response = getCitiesUseCase()
        val resource = response.first()
        val data = (resource as Resource.Success).data

        assertThat(data).isEqualTo(cityDummyList)
    }

    @Test
    fun `Load cities use case returns error`() = runBlocking {
        val getCitiesUseCase = GetCitiesUseCase(fakeCityRepository)
        fakeCityRepository.setShouldReturnNetworkError(true)

        val response = getCitiesUseCase()
        val resource = response.first()

        assertThat(resource is Resource.Error).isTrue()
    }

}
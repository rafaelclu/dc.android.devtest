package dc.android.devtest.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import dc.android.devtest.presentation.DestinationKeys.CITY_ID_KEY
import dc.android.devtest.presentation.MainDestinations.CITY_LIST_ROUTE
import dc.android.devtest.presentation.MainDestinations.WEATHER_DETAILS_ROUTE
import dc.android.devtest.presentation.city_list.CityListScreen
import dc.android.devtest.presentation.weather_detail.WeatherDetailScreen

object MainDestinations {
    const val CITY_LIST_ROUTE = "city_list_route"
    const val WEATHER_DETAILS_ROUTE = "weather_details_route"
}

object DestinationKeys {
    const val CITY_ID_KEY = "city_id"
}

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(navController = navController, startDestination = CITY_LIST_ROUTE) {
        composable(route = CITY_LIST_ROUTE) {
            CityListScreen(navigateToWeatherDetails = actions.navigateToWeatherDetails)
        }
        composable(
            route = "$WEATHER_DETAILS_ROUTE/{$CITY_ID_KEY}",
            arguments = listOf(navArgument(CITY_ID_KEY) { type = NavType.StringType })
        ) { backStackEntry ->
            WeatherDetailScreen(backStackEntry.arguments!!.getString(CITY_ID_KEY)!!)
        }
    }
}

class MainActions(navController: NavHostController) {
    val navigateToWeatherDetails: (String) -> Unit = { cityId ->
        navController.navigate("$WEATHER_DETAILS_ROUTE/$cityId")
    }
}
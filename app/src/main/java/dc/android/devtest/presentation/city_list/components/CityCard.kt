package dc.android.devtest.presentation.city_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dc.android.devtest.presentation.city_list.model.CityModel
import dc.android.devtest.presentation.ui.theme.darkPurple
import dc.android.devtest.presentation.ui.theme.lightPurple

@Composable
fun CityCard(
    modifier: Modifier = Modifier,
    cityModel: CityModel,
    onCardClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable { onCardClick(cityModel.cityId) },
        shape = RoundedCornerShape(2.dp),
        elevation = 2.dp,
        backgroundColor = MaterialTheme.colors.lightPurple
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = cityModel.cityName,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.darkPurple
            )
            Text(
                text = " - ",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.darkPurple
            )
            Text(
                text = cityModel.cityState,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.darkPurple
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CityCardPreview() {
    CityCard(
        cityModel = CityModel(
            cityId = "",
            cityName = "Recife",
            cityState = "Pernambuco"
        ),
        onCardClick = {}
    )
}
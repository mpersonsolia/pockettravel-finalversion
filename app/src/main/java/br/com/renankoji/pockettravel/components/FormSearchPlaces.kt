import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.renankoji.pockettravel.R
import br.com.renankoji.pockettravel.components.ButtonGo
import br.com.renankoji.pockettravel.components.ButtonOptionItem
import br.com.renankoji.pockettravel.repository.CategoriesRepo
import br.com.renankoji.pockettravel.repository.CitiesRepo
import java.util.Locale

@Composable
fun FormSearchPlaces(navController: NavController) {
    val cities = CitiesRepo()
    val categories = CategoriesRepo()

    val chosenCity = remember {
        mutableStateOf("")
    }
    val chosenCategory = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(1.dp))
        // cities list
        Text(
            text = stringResource(id = R.string.dashboard_cities).uppercase(Locale.getDefault()),
            modifier = Modifier
                .fillMaxWidth(),
            color = colorResource(id = R.color.title_color),
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 5.dp),
        ) {
            items(cities) { city ->
                ButtonOptionItem(title = city.title,
                    backgroundColor = if (chosenCity.value == city.title) colorResource(id = R.color.button_color) else colorResource(
                        id = R.color.primary_500
                    ),
                    icon = city.icon,
                    borderColor = Color.Transparent,
                    shape = RoundedCornerShape(16.dp),
                    onClick = { chosenCity.value = city.title })
            }
        }

        // categories list
        Text(
            text = stringResource(id = R.string.dashboard_categories).uppercase(Locale.getDefault()),
            modifier = Modifier
                .fillMaxWidth(),
            color = colorResource(id = R.color.title_color),
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 5.dp)
        ) {
            items(categories) { category ->
                ButtonOptionItem(title = category.title,
                    backgroundColor = if (chosenCategory.value == category.featureClass) colorResource(
                        id = R.color.button_color
                    ) else colorResource(
                        id = R.color.primary_500
                    ),
                    icon = category.icon,
                    borderColor = Color.Transparent,
                    shape = RoundedCornerShape(16.dp),
                    onClick = { chosenCategory.value = category.featureClass })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ButtonGo(
                onClick = { navController.navigate("city/${chosenCity.value}/${chosenCategory.value}") },
                enabled = chosenCity.value.isNotEmpty() && chosenCategory.value.isNotEmpty(),
                modifier = Modifier,
                text = stringResource(id = R.string.dashboard_button_search).uppercase(Locale.getDefault())
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormPreview() {
    val navController = rememberNavController()
    FormSearchPlaces(navController)
}
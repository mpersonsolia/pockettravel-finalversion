package br.com.renankoji.pockettravel.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddRoad
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.NaturePeople
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Water
import androidx.compose.material.icons.filled.WaterfallChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import br.com.renankoji.pockettravel.R

data class Categories (
    val id: Int,
    val title: String,
    val featureClass: String,
    val icon: ImageVector
)

@Composable
fun CategoriesRepo(): List<Categories> {
    return listOf(
        Categories(id = 0, title = stringResource(id = R.string.dashboard_categories_city), featureClass = "P", icon = Icons.Filled.LocationCity),
        Categories(id = 2, title = stringResource(id = R.string.dashboard_categories_aquatic), featureClass = "H", icon = Icons.Filled.Water),
        Categories(id = 3, title = stringResource(id = R.string.dashboard_categories_parks), featureClass = "L", icon = Icons.Filled.Forest),
        Categories(id = 4, title = stringResource(id = R.string.dashboard_categories_transport), featureClass = "S", icon = Icons.Filled.DirectionsCar),
        Categories(id = 5, title = stringResource(id = R.string.dashboard_categories_mountain), featureClass = "T", icon = Icons.Filled.NaturePeople),
    )
}
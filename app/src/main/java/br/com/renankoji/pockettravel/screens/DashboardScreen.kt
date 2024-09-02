package br.com.renankoji.pockettravel.screens

import FormSearchPlaces
import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.renankoji.pockettravel.R
import br.com.renankoji.pockettravel.api.calculateDistance
import br.com.renankoji.pockettravel.components.CardImagePlaces
import br.com.renankoji.pockettravel.components.DashboardHeader
import br.com.renankoji.pockettravel.components.Input
import br.com.renankoji.pockettravel.components.karlaFont
import br.com.renankoji.pockettravel.repository.ImagesLastSeenPlaces
import br.com.renankoji.pockettravel.repository.ImagesTopVisited
import java.util.Locale

@Composable
fun DashboardScreen(
    userLatitude: Double?,
    userLongitude: Double?,
    hasLocationPermission: Boolean,
    navController: NavController
) {
    val imagesTopPlaces = ImagesTopVisited()
    val placesLastSeen = ImagesLastSeenPlaces()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.background_color),
            )
            .verticalScroll(scrollState)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            if (!hasLocationPermission) {
                Text(
                    text = stringResource(id = R.string.dashboard_lastseen_location_permission),
                    fontFamily = karlaFont
                )
            }

            DashboardHeader()

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Spacer(modifier = Modifier.height(0.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Input(
                        value = "",
                        modifier = Modifier
                            .width(390.dp),
                        keyboardType = KeyboardType.Uri,
                        placeholder = stringResource(id = R.string.dashboard_input),
                        placeholderColor = colorResource(id = R.color.gray_500),
                        unfocusedContainerColor = colorResource(id = R.color.white),
                        focusedContainerColor = colorResource(id = R.color.white),
                        unfocusedBorderColor = colorResource(id = R.color.primary_500),
                        focusedBorderColor = colorResource(id = R.color.button_color),
                        icon = Icons.Filled.Search,
                        iconColor = colorResource(id = R.color.button_color)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = 36.dp,
                                topEnd = 36.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        )
                        .padding(0.dp),
                    colors = CardDefaults.cardColors(colorResource(id = R.color.background_color_light)),
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(16.dp))

                        //search form
                        FormSearchPlaces(navController)

                        Spacer(modifier = Modifier.height(16.dp))

                        //most visited
                        Text(
                            text = stringResource(id = R.string.dashboard_most_visited).uppercase(
                                Locale.getDefault()
                            ),
                            modifier = Modifier
                                .padding(horizontal = 32.dp)
                                .fillMaxWidth(),
                            color = colorResource(id = R.color.title_color),
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        LazyRow(contentPadding = PaddingValues(horizontal = 32.dp)) {
                            items(imagesTopPlaces) { card ->
                                CardImagePlaces(
                                    painter = painterResource(id = card.imageResId),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(200.dp)
                                        .height(180.dp)
                                        .padding(end = 8.dp)
                                        .clip(RoundedCornerShape(16.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        //last seen list
                        Text(
                            text = stringResource(id = R.string.dashboard_lastseen).uppercase(Locale.getDefault()),
                            modifier = Modifier
                                .padding(horizontal = 32.dp)
                                .fillMaxWidth(),
                            color = colorResource(id = R.color.title_color),
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        LazyRow(contentPadding = PaddingValues(horizontal = 32.dp)) {
                            items(placesLastSeen) { placeLastSeen ->
                                var formattedDistance =
                                    stringResource(id = R.string.dashboard_lastseen_not_available)

                                if (userLatitude != null && userLongitude != null) {
                                    val dist = calculateDistance(
                                        userLatitude,
                                        userLongitude,
                                        placeLastSeen.latitude,
                                        placeLastSeen.longitude
                                    )
                                    val format = DecimalFormat("#,###")
                                    formattedDistance = "${format.format(dist)} km"
                                }

                                Column {
                                    CardImagePlaces(
                                        painter = painterResource(id = placeLastSeen.imageResId),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(200.dp)
                                            .height(180.dp)
                                            .padding(end = 8.dp)
                                            .clip(RoundedCornerShape(16.dp)),
                                        contentScale = ContentScale.Crop
                                    )
                                    Text(text = placeLastSeen.location)
                                    Text(text = stringResource(id = R.string.dashboard_lastseen_distance) + " " + formattedDistance)
                                }
                            }
                        }

                    }
                }

            }
        }
    }
}
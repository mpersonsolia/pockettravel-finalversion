package br.com.renankoji.pockettravel.repository

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import br.com.renankoji.pockettravel.R

data class ImagesLastSeen(
    val id: Int,
    val location: String,
    val latitude: Double,
    val longitude: Double,
    val imageResId: Int
)

@Composable
fun ImagesLastSeenPlaces(): List<ImagesLastSeen> {
    return listOf(
        ImagesLastSeen(
            id = 1,
            location = stringResource(id = R.string.dashboard_lastseen_museumoftomorrow),
            latitude = -22.8939,
            longitude = -43.1794,
            imageResId = R.drawable.museudoamanha
        ),
        ImagesLastSeen(
            id = 2,
            location = stringResource(id = R.string.dashboard_lastseen_christtheredeemer),
            latitude = -22.9519,
            longitude = -43.2105,
            imageResId = R.drawable.cristoredentor
        ),
        ImagesLastSeen(
            id = 3,
            location = stringResource(id = R.string.dashboard_lastseen_copacabanabeach),
            latitude = -22.9711,
            longitude = -43.1825,
            imageResId = R.drawable.praiadecopacabana
        ),
        ImagesLastSeen(
            id = 4,
            location = stringResource(id = R.string.dashboard_lastseen_lagepark),
            latitude = -22.9607,
            longitude = -43.2121,
            imageResId = R.drawable.parquelage
        ),
        ImagesLastSeen(
            id = 5,
            location = stringResource(id = R.string.dashboard_lastseen_catetepalace),
            latitude = -22.9259,
            longitude = -43.1762,
            imageResId = R.drawable.palaciodocatete
        ),
        ImagesLastSeen(
            id = 6,
            location = stringResource(id = R.string.dashboard_lastseen_arpoador),
            latitude = -22.9687,
            longitude = -43.1807,
            imageResId = R.drawable.arpoador
        ),
        ImagesLastSeen(
            id = 7,
            location = stringResource(id = R.string.dashboard_lastseen_selaronsteps),
            latitude = -22.9155,
            longitude = -43.1796,
            imageResId = R.drawable.escadariaselaron
        ),
        ImagesLastSeen(
            id = 8,
            location =stringResource(id = R.string.dashboard_lastseen_ipanemabeach),
            latitude = -22.9847,
            longitude = -43.1986,
            imageResId = R.drawable.praiadeipanema
        )
    )
}
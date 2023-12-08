package br.com.devmeist3r.core.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.devmeist3r.core.util.Constants.MOVIE_DETAIL_ARGUMENT_KEY

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    object MoviePopular: BottomNavItem (
        title = "Filmes Populares",
        icon = Icons.Default.Movie,
        route = "movie_popular_screen"
    )

    object MovieSearch: BottomNavItem (
        title = "Pesquisar",
        icon = Icons.Default.Search,
        route = "movie_search_screen"
    )

    object MovieFavorites: BottomNavItem (
        title = "Favoritos",
        icon = Icons.Default.Favorite,
        route = "movie_favorite_screen"
    )

    object MovieDetail: BottomNavItem (
        title = "Detalhes",
        icon = Icons.Default.Favorite,
        route = "movie_detail_destination?$MOVIE_DETAIL_ARGUMENT_KEY={$MOVIE_DETAIL_ARGUMENT_KEY}"
    ) {
        fun passMovieId(movieId: Int) = "movie_detail_destination?$MOVIE_DETAIL_ARGUMENT_KEY=$movieId"
    }
}
package br.com.devmeist3r.core.presentation.navigation

import br.com.devmeist3r.core.util.Constants

sealed class DetailScreenNav(val route: String) {
    object DetailScreen : DetailScreenNav(
        route = "movie_detail_destination?${Constants.MOVIE_DETAIL_ARGUMENT_KEY}={${Constants.MOVIE_DETAIL_ARGUMENT_KEY}}"
    ) {
        fun passMovieId(movieId: Int) =
            "movie_detail_destination?${Constants.MOVIE_DETAIL_ARGUMENT_KEY}=$movieId"
    }
}
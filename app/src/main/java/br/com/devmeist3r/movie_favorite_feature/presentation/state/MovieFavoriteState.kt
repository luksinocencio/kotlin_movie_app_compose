package br.com.devmeist3r.movie_favorite_feature.presentation.state

import br.com.devmeist3r.core.domain.model.Movie

data class MovieFavoriteState(
    val movies: List<Movie> = emptyList()
)

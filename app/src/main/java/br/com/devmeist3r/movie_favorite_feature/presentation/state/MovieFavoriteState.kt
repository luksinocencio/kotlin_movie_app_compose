package br.com.devmeist3r.movie_favorite_feature.presentation.state

import br.com.devmeist3r.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieFavoriteState(
  val movies: Flow<List<Movie>> = emptyFlow()
)

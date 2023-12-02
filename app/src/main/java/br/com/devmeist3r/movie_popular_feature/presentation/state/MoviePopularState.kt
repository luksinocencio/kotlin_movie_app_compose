package br.com.devmeist3r.movie_popular_feature.presentation.state

import androidx.paging.PagingData
import br.com.devmeist3r.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MoviePopularState(
    val movies: Flow<PagingData<Movie>> = emptyFlow()
)
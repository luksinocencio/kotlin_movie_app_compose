package br.com.devmeist3r.movie_popular_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.devmeist3r.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviePopularRepository {
    fun getPopularMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>>
}
package br.com.devmeist3r.movie_detail_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun getMoviesSimilar(movieId: Int, pagingConfig: PagingConfig): Flow<PagingData<Movie>>
}
package br.com.devmeist3r.movie_detail_feature.domain.source

import br.com.devmeist3r.core.domain.model.MovieDetails
import br.com.devmeist3r.core.domain.model.MoviePaging
import br.com.devmeist3r.core.paging.MovieSimilarPagingSource

interface MovieDetailsRemoteDataSource {
    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun getMoviesSimilar(page: Int, movieId: Int): MoviePaging
    fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource
}
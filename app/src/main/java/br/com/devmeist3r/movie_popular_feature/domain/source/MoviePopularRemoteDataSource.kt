package br.com.devmeist3r.movie_popular_feature.domain.source

import br.com.devmeist3r.core.domain.model.MoviePaging
import br.com.devmeist3r.core.paging.MoviePagingSource

interface MoviePopularRemoteDataSource {
    fun getPopularMoviesPagingSource(): MoviePagingSource
    suspend fun getPopularMovies(page: Int): MoviePaging
}
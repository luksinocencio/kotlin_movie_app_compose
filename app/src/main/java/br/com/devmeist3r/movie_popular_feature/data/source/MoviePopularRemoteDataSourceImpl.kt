package br.com.devmeist3r.movie_popular_feature.data.source

import br.com.devmeist3r.core.data.remote.MovieService
import br.com.devmeist3r.core.data.remote.response.MovieResponse
import br.com.devmeist3r.core.paging.MoviePagingSource
import br.com.devmeist3r.movie_popular_feature.domain.source.MoviePopularRemoteDataSource

class MoviePopularRemoteDataSourceImpl constructor(
    private val service: MovieService
): MoviePopularRemoteDataSource {
    override fun getPopularMoviesPagingSource(): MoviePagingSource {
        return MoviePagingSource(this)
    }

    override suspend fun getPopularMovies(page: Int): MovieResponse {
        return service.getPopularMovies(page = page)
    }
}
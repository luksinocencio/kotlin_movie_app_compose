package br.com.devmeist3r.movie_popular_feature.data.source

import br.com.devmeist3r.core.data.remote.MovieService
import br.com.devmeist3r.core.data.remote.response.MovieResponse
import br.com.devmeist3r.core.domain.model.MoviePaging
import br.com.devmeist3r.core.paging.MoviePagingSource
import br.com.devmeist3r.movie_popular_feature.data.mapper.toMovie
import br.com.devmeist3r.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import javax.inject.Inject

class MoviePopularRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MoviePopularRemoteDataSource {
    override fun getPopularMoviesPagingSource(): MoviePagingSource {
        return MoviePagingSource(this)
    }

    override suspend fun getPopularMovies(page: Int): MoviePaging {
      val response = service.getPopularMovies(page = page)
      return MoviePaging(
        page = response.page,
        totalResults = response.totalResults,
        totalPages = response.totalPages,
        movies = response.results.map { it.toMovie() }
      )
    }
}
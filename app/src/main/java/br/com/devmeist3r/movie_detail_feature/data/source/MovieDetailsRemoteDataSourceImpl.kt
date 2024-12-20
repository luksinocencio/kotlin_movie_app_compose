package br.com.devmeist3r.movie_detail_feature.data.source

import br.com.devmeist3r.core.data.remote.MovieService
import br.com.devmeist3r.core.data.remote.response.MovieResponse
import br.com.devmeist3r.core.domain.model.MovieDetails
import br.com.devmeist3r.core.domain.model.MoviePaging
import br.com.devmeist3r.core.paging.MovieSimilarPagingSource
import br.com.devmeist3r.core.util.toBackdropUrl
import br.com.devmeist3r.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import br.com.devmeist3r.movie_popular_feature.data.mapper.toMovie
import javax.inject.Inject

class MovieDetailsRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
): MovieDetailsRemoteDataSource {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val response = service.getMovie(movieId)
        val genres = response.genres.map { it.name }
        return MovieDetails(
            id = response.id,
            title = response.title,
            overview = response.overview,
            genres = genres,
            releaseDate = response.releaseDate,
            backdropPathUrl = response.backdropPath.toBackdropUrl(),
            voteAverage = response.voteAverage,
            duration = response.runtime,
            voteCount = response.voteCount
        )
    }

    override suspend fun getMoviesSimilar(page: Int, movieId: Int): MoviePaging {
      val response = service.getMoviesSimilar(page = page, movieId = movieId)

      return MoviePaging(
        page = response.page,
        totalPages = response.totalPages,
        totalResults = response.totalResults,
        movies = response.results.map { it.toMovie() }
      )
    }

    override fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource {
        return MovieSimilarPagingSource(this, movieId = movieId)
    }
}
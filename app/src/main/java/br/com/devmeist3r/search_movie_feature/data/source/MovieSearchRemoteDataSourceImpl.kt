package br.com.devmeist3r.search_movie_feature.data.source

import br.com.devmeist3r.core.data.remote.MovieService
import br.com.devmeist3r.core.domain.model.MovieSearchPaging
import br.com.devmeist3r.core.paging.MovieSearchPagingSource
import br.com.devmeist3r.search_movie_feature.data.mapper.toMovieSearch
import br.com.devmeist3r.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
): MovieSearchRemoteDataSource {
    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource {
        return MovieSearchPagingSource(query = query, remoteDataSource = this)
    }

    override suspend fun getSearchMovies(page: Int, query: String): MovieSearchPaging {
      val response = service.searchMovie(page = page, query = query)

      return MovieSearchPaging(
          page = response.page,
          totalPages = response.totalPages,
          totalResults = response.totalResults,
          movies = response.results.map { it.toMovieSearch() }
      )
    }
}
package br.com.devmeist3r.search_movie_feature.data.repository

import androidx.paging.PagingSource
import br.com.devmeist3r.core.domain.model.MovieSearch
import br.com.devmeist3r.core.paging.MovieSearchPagingSource
import br.com.devmeist3r.search_movie_feature.domain.repository.MovieSearchRepository
import br.com.devmeist3r.search_movie_feature.domain.source.MovieSearchRemoteDataSource

class MovieSearchRepositoryImpl constructor(
    private val remoteDataSource: MovieSearchRemoteDataSource
): MovieSearchRepository {
  override fun getSearchMovies(query: String): PagingSource<Int, MovieSearch> {
    return MovieSearchPagingSource(query, remoteDataSource)
  }
}
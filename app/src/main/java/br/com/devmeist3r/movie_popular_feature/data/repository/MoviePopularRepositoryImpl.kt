package br.com.devmeist3r.movie_popular_feature.data.repository

import androidx.paging.PagingSource
import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.core.paging.MoviePagingSource
import br.com.devmeist3r.movie_popular_feature.domain.repository.MoviePopularRepository
import br.com.devmeist3r.movie_popular_feature.domain.source.MoviePopularRemoteDataSource

class MoviePopularRepositoryImpl(
  private val remoteDataSource: MoviePopularRemoteDataSource
) : MoviePopularRepository {
  override fun getPopularMovies(): PagingSource<Int, Movie> {
    return MoviePagingSource(remoteDataSource)
  }
}
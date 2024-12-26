package br.com.devmeist3r.movie_detail_feature.data.repository

import androidx.paging.PagingSource
import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.core.domain.model.MovieDetails
import br.com.devmeist3r.core.paging.MovieSimilarPagingSource
import br.com.devmeist3r.movie_detail_feature.domain.repository.MovieDetailsRepository
import br.com.devmeist3r.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
  private val remoteDataSource: MovieDetailsRemoteDataSource
) : MovieDetailsRepository {
  override suspend fun getMovieDetails(movieId: Int): MovieDetails {
    return remoteDataSource.getMovieDetails(movieId = movieId)
  }

  override fun getMoviesSimilar(
    movieId: Int
  ): PagingSource<Int, Movie> {
    return MovieSimilarPagingSource(movieId = movieId, remoteDataSource = remoteDataSource)
  }
}
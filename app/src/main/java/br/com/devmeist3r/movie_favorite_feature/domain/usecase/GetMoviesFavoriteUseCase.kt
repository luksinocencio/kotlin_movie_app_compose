package br.com.devmeist3r.movie_favorite_feature.domain.usecase

import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.core.util.ResultData
import br.com.devmeist3r.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetMoviesFavoriteUseCase {
    suspend fun invoke(): Flow<List<Movie>>
}

class GetMoviesFavoriteUseCaseImpl @Inject constructor(
    private val movieFavoriteRepository: MovieFavoriteRepository
) : GetMoviesFavoriteUseCase {
    override suspend fun invoke(): Flow<List<Movie>> {
      return try {
        movieFavoriteRepository.getMovies()
      } catch (e: Exception) {
         emptyFlow()
      }
    }
}
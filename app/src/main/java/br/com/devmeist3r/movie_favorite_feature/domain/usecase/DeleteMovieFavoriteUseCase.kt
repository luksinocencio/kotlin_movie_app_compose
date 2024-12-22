package br.com.devmeist3r.movie_favorite_feature.domain.usecase

import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.core.util.ResultData
import br.com.devmeist3r.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface DeleteMovieFavoriteUseCase {
  suspend fun invoke(params: Params): Flow<ResultData<Unit>>
  data class Params(val movie: Movie)
}

class DeleteMovieFavoriteUseCaseImpl @Inject constructor(
  private val movieFavoriteRepository: MovieFavoriteRepository
) : DeleteMovieFavoriteUseCase {
  override suspend fun invoke(params: DeleteMovieFavoriteUseCase.Params): Flow<ResultData<Unit>> {
    return flow {
      try {
        val insert = movieFavoriteRepository.delete(params.movie)
        emit(ResultData.Success(insert))
      } catch (e: Exception) {
        emit(ResultData.Failure(e))
      }
    }.flowOn(Dispatchers.IO)
  }
}
package br.com.devmeist3r.movie_favorite_feature.domain.usecase

import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.core.util.ResultData
import br.com.devmeist3r.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface IsMovieFavoriteUseCase {
    suspend fun invoke(params: Params): Flow<ResultData<Boolean>>
    data class Params(val movieId: Int)
}

class IsMovieFavoriteUseCaseImpl @Inject constructor(
    private val movieFavoriteRepository: MovieFavoriteRepository
) : IsMovieFavoriteUseCase {
    override suspend fun invoke(params: IsMovieFavoriteUseCase.Params): Flow<ResultData<Boolean>> {
        return flow {
            val isFavorite = movieFavoriteRepository.isFavorite(params.movieId)
            emit(ResultData.Success(isFavorite))
        }.flowOn(Dispatchers.IO)
    }
}
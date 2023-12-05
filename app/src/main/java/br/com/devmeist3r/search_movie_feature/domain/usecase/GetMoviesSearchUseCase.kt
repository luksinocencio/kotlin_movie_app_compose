package br.com.devmeist3r.search_movie_feature.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.devmeist3r.core.domain.model.MovieSearch
import br.com.devmeist3r.search_movie_feature.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMoviesSearchUseCase {
    operator fun invoke(params: Params): Flow<PagingData<MovieSearch>>
    data class Params(val query: String)
}

class GetMoviesSearchUseCaseImpl @Inject constructor(
    private val repository: MovieSearchRepository
): GetMoviesSearchUseCase {
    override fun invoke(params: GetMoviesSearchUseCase.Params): Flow<PagingData<MovieSearch>> {
        return repository.getSearchMovies(
            query = params.query,
            pagingSource = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            )
        )
    }
}
package br.com.devmeist3r.search_movie_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.devmeist3r.core.domain.model.MovieSearch
import br.com.devmeist3r.search_movie_feature.domain.repository.MovieSearchRepository
import br.com.devmeist3r.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MovieSearchRepositoryImpl constructor(
    private val remoteDataSource: MovieSearchRemoteDataSource
): MovieSearchRepository {
    override fun getSearchMovies(
        query: String,
        pagingSource: PagingConfig
    ): Flow<PagingData<MovieSearch>> {
        return Pager(
            config = pagingSource,
            pagingSourceFactory = { remoteDataSource.getSearchMoviePagingSource(query = query)}
        ).flow
    }
}
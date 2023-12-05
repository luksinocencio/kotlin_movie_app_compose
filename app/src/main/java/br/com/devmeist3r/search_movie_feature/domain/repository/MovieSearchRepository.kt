package br.com.devmeist3r.search_movie_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.devmeist3r.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {
    fun getSearchMovies(query: String, pagingSource: PagingConfig): Flow<PagingData<MovieSearch>>
}
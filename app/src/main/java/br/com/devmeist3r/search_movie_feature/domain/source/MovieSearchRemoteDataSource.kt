package br.com.devmeist3r.search_movie_feature.domain.source

import br.com.devmeist3r.core.data.remote.response.SearchResponse
import br.com.devmeist3r.core.paging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {
    fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource
    suspend fun getSearchMovies(page: Int, query: String): SearchResponse
}
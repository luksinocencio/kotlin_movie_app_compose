package br.com.devmeist3r.search_movie_feature.domain.repository

import androidx.paging.PagingSource
import br.com.devmeist3r.core.domain.model.MovieSearch

interface MovieSearchRepository {
    fun getSearchMovies(query: String): PagingSource<Int, MovieSearch>
}
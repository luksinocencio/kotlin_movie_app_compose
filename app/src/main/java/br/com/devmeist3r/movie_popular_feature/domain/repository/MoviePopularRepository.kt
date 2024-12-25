package br.com.devmeist3r.movie_popular_feature.domain.repository

import androidx.paging.PagingSource
import br.com.devmeist3r.core.domain.model.Movie

interface MoviePopularRepository {
  fun getPopularMovies(): PagingSource<Int, Movie>
}
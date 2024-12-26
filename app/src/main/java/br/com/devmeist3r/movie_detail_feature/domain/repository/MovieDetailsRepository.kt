package br.com.devmeist3r.movie_detail_feature.domain.repository

import androidx.paging.PagingSource
import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.core.domain.model.MovieDetails

interface MovieDetailsRepository {
  suspend fun getMovieDetails(movieId: Int): MovieDetails
  fun getMoviesSimilar(movieId: Int): PagingSource<Int, Movie>
}
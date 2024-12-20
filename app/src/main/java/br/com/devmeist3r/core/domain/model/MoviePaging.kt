package br.com.devmeist3r.core.domain.model

data class MoviePaging(
  val page: Int,
  val totalPages: Int,
  val totalResults: Int,
  val movies: List<Movie>
)

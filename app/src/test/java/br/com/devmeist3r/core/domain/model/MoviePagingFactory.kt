package br.com.devmeist3r.core.domain.model

class MoviePagingFactory {
  fun create() = MoviePaging(
    page = 1,
    totalPages = 1,
    totalResults = 2,
    movies = listOf(
      Movie(id = 1, title = "Avengers", voteAverage = 7.1, imageUrl = "any_url"),
      Movie(id = 2, title = "John Wick", voteAverage = 7.9, imageUrl = "any_url")
    )
  )
}
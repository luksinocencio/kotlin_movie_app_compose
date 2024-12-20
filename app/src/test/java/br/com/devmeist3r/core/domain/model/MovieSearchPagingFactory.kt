package br.com.devmeist3r.core.domain.model

class MovieSearchPagingFactory {
  fun create() = MovieSearchPaging(
    page = 1,
    totalPages = 1,
    totalResults = 1,
    movies = listOf(
      MovieSearch(
        id = 1,
        voteAverage = 7.1,
        imageUrl = "any_url"
      ),
      MovieSearch(
        id = 2,
        voteAverage = 7.9,
        imageUrl = "any_url"
      )
    )
  )
}
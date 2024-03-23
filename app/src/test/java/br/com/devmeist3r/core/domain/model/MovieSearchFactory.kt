package br.com.devmeist3r.core.domain.model

class MovieSearchFactory {
  fun create(poster: Poster) = when(poster) {
    Poster.Avengers -> {
      MovieSearch(
        id = 1,
        voteAverage = 7.1,
        imageUrl = "any_url"
      )
    }
    Poster.JohnWick -> {
      MovieSearch(
        id = 2,
        voteAverage = 7.9,
        imageUrl = "any_url"
      )
    }
  }

  sealed class Poster {
    object Avengers: Poster()
    object JohnWick: Poster()
  }
}
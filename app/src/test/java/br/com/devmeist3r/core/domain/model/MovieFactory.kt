package br.com.devmeist3r.core.domain.model

class MovieFactory {
  fun create(poster: Poster) = when(poster) {
    Poster.Avengers -> {
      Movie(id = 1, title = "Avengers", voteAverage = 7.1, imageUrl = "any_url")
    }
    Poster.JohnWick -> {
      Movie(id = 2, title = "John Wick", voteAverage = 7.9, imageUrl = "any_url")
    }
  }

  sealed class Poster {
    object Avengers: Poster()
    object JohnWick: Poster()
  }
}
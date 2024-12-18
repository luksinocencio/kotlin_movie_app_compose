package br.com.devmeist3r.core.domain.model

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

class MovieDetailsFactory {
  fun create(poster: Poster ) = when (poster) {
    Poster.Avengers -> {
      MovieDetails(
        id = 1,
        title = "Avengers",
        voteAverage = 8.1,
        genres = listOf("Aventura, Ação", "Ficção científica"),
        overview = LoremIpsum(100).values.first(),
        backdropPathUrl = "Url",
        releaseDate = "04/05/2012",
        duration = 143,
        voteCount = 7
      )
    }

    Poster.JohnWick -> {
      MovieDetails(
        id = 2,
        title = "John Wick",
        voteAverage = 7.1,
        genres = listOf("Aventura, Ação", "Ficção científica"),
        overview = LoremIpsum(100).values.first(),
        backdropPathUrl = "Url",
        releaseDate = "04/05/2012",
        duration = 143,
        voteCount = 7
      )
    }
  }

  sealed class Poster {
    object Avengers : Poster()
    object JohnWick : Poster()
  }
}
package br.com.devmeist3r.movie_popular_feature.data.mapper

import br.com.devmeist3r.core.data.remote.model.MovieResult
import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.core.util.toPostUrl

fun MovieResult.toMovie(): Movie {
  return Movie(
    id = id,
    title = title,
    voteAverage = voteAverage,
    imageUrl = posterPath?.toPostUrl() ?: ""
  )
}

fun List<MovieResult>.toMovie() = map {movieResult ->
    Movie(
        id = movieResult.id,
        title = movieResult.title,
        voteAverage = movieResult.voteAverage,
        imageUrl = movieResult.posterPath?.toPostUrl() ?: ""
    )
}
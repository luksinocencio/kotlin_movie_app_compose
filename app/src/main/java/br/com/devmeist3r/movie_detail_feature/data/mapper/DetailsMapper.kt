package br.com.devmeist3r.movie_detail_feature.data.mapper

import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.core.domain.model.MovieDetails

fun MovieDetails.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        imageUrl = backdropPathUrl.toString(),
        voteAverage = voteAverage
    )
}
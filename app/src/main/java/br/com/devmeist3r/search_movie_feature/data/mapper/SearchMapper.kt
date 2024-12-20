package br.com.devmeist3r.search_movie_feature.data.mapper

import br.com.devmeist3r.core.data.remote.model.SearchResult
import br.com.devmeist3r.core.domain.model.MovieSearch
import br.com.devmeist3r.core.util.toPostUrl

fun SearchResult.toMovieSearch(): MovieSearch {
  return MovieSearch(
    id = id,
    imageUrl = posterPath.toPostUrl(),
    voteAverage = voteAverage
  )
}

fun List<SearchResult>.toMovieSearch() = map {searchResult ->
    MovieSearch(
        id = searchResult.id,
        imageUrl = searchResult.posterPath.toPostUrl(),
        voteAverage = searchResult.voteAverage
    )
}
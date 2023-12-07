package br.com.devmeist3r.movie_detail_feature.presentation

sealed class MovieDetailEvent {
    data class GetMovieDetail(val movieId: Int): MovieDetailEvent()
}

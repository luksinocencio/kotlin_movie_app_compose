package br.com.devmeist3r.movie_favorite_feature.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.devmeist3r.R
import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.movie_favorite_feature.presentation.components.MovieFavoriteContent
import br.com.devmeist3r.movie_favorite_feature.presentation.state.MovieFavoriteState
import br.com.devmeist3r.ui.theme.black
import br.com.devmeist3r.ui.theme.white

@Composable
fun MovieFavoriteScreen(
  movies: List<Movie>,
  navigateToDetailMovie: (Int) -> Unit
) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = stringResource(id = R.string.favorite_movies),
            color = white
          )
        },
        backgroundColor = black
      )
    },
    content = { paddingValues ->
      MovieFavoriteContent(
        movies = movies,
        paddingValues = paddingValues,
        onClick = { movieId ->
          navigateToDetailMovie(movieId)
        })
    }
  )
}

@Preview
@Composable
fun MovieFavoriteScreenPreview() {
  MovieFavoriteScreen(
    movies = emptyList(),
  ) {}
}
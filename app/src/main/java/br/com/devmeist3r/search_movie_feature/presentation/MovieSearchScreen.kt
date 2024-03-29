package br.com.devmeist3r.search_movie_feature.presentation

import androidx.compose.material.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.devmeist3r.R
import br.com.devmeist3r.core.presentation.components.common.MovieAppBar
import br.com.devmeist3r.search_movie_feature.presentation.components.SearchContent
import br.com.devmeist3r.search_movie_feature.presentation.state.MovieSearchState
import br.com.devmeist3r.ui.theme.black
import br.com.devmeist3r.ui.theme.white

@Composable
fun MovieSearchScreen(
  uiState: MovieSearchState,
  onEvent: (MovieSearchEvent) -> Unit,
  onFetch: (String) -> Unit,
  navigateToDetailMovie: (Int) -> Unit
) {
  val pagingMovies = uiState.movies.collectAsLazyPagingItems()

  Scaffold(
    topBar = {
      MovieAppBar(title = R.string.search_movies)
    },
    content = { paddingValues ->
      SearchContent(
        paddingValues = paddingValues,
        pagingMovies = pagingMovies,
        query = uiState.query,
        onSearch = {
          onFetch(it)
        },
        onEvent = {
          onEvent(it)
        },
        onDetail = { movieId ->
          navigateToDetailMovie(movieId)
        }
      )
    }
  )
}

@Preview
@Composable
fun MovieSearchScreenPreview() {

}
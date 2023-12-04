package br.com.devmeist3r.movie_popular_feature.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.devmeist3r.R
import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.core.util.UtilFunctions
import br.com.devmeist3r.movie_popular_feature.presentation.components.MovieContent
import br.com.devmeist3r.movie_popular_feature.presentation.state.MoviePopularState
import br.com.devmeist3r.ui.theme.black
import br.com.devmeist3r.ui.theme.white

@Composable
fun MoviePopularScreen(
    uiState: MoviePopularState,
    navigateToDetailMovie: (Int) -> Unit
) {
    val movies: LazyPagingItems<Movie> = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.popular_movies),
                        color = white
                    )
                },
                backgroundColor = black
            )
        },
        content = { paddingValues ->
            MovieContent(
                pagingMovies = movies,
                paddingValues = paddingValues,
                onClick = { movieId ->
                    UtilFunctions.logInfo("MOVIE_ID", movieId.toString())
                    navigateToDetailMovie(movieId)
                }
            )
        }
    )
}
package br.com.devmeist3r.movie_detail_feature.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.devmeist3r.R
import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.movie_detail_feature.presentation.components.MovieDetailsContent
import br.com.devmeist3r.movie_detail_feature.presentation.state.MovieDetailState
import br.com.devmeist3r.ui.theme.black
import br.com.devmeist3r.ui.theme.white

@Composable
fun MovieDetailScreen(
    uiState: MovieDetailState,
    onAddFavorite: (Movie) -> Unit
) {
    val pagingMoviesSimilar = uiState.results.collectAsLazyPagingItems()

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.detail_movie), color = white)
                },
                backgroundColor = black
            )
        },
        content = { _ ->
            MovieDetailsContent(
                movieDetails = uiState.movieDetails,
                pagingMoviesSimilar = pagingMoviesSimilar,
                isLoading = uiState.isLoading,
                isError = uiState.error,
                iconColor = uiState.iconColor,
                onAddFavorite = {
                    onAddFavorite(it)
                }
            )
        }
    )
}
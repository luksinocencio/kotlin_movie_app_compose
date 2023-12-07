package br.com.devmeist3r.movie_detail_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.core.presentation.components.common.ErrorScreen
import br.com.devmeist3r.core.presentation.components.common.LoadingView
import br.com.devmeist3r.movie_popular_feature.presentation.components.MovieItem
import kotlinx.coroutines.flow.flowOf

@Composable
fun MovieDetailSimilarMovies(
    pagingMoviesSimilar: LazyPagingItems<Movie>,
    modifier: Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        items(pagingMoviesSimilar.itemCount) { index ->
            val movie = pagingMoviesSimilar[index]
            movie?.let {
                MovieItem(
                    modifier = modifier,
                    voteAverage = it.voteAverage,
                    imageUrl = it.imageUrl,
                    id = it.id,
                    onClick = {}
                )
            }
        }

        pagingMoviesSimilar.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        LoadingView()
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        LoadingView()
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = pagingMoviesSimilar.loadState.refresh as LoadState.Error
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        ErrorScreen(message = error.error.message.toString()) {
                            retry()
                        }
                    }
                }

                loadState.append is LoadState.Error -> {
                    val error = pagingMoviesSimilar.loadState.refresh as LoadState.Error
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        ErrorScreen(message = error.error.message.toString()) {
                            retry()
                        }
                    }
                }
            }
        }
    }
}
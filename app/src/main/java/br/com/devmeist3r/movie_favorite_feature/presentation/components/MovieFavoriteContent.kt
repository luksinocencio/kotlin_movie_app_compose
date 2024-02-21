package br.com.devmeist3r.movie_favorite_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.ui.theme.black

@Composable
fun MovieFavoriteContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    movies: List<Movie>,
    onClick: (id: Int) -> Unit
) {
    Box(modifier = modifier.background(black)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = paddingValues,
            content = {
                items(
                    items = movies,
                    key = { item: Movie ->
                        item.id
                    }
                ) { movie ->
                    MovieFavoriteItem(movie = movie, onClick = onClick)
                }
            }
        )
    }
}

@Preview
@Composable
fun MovieFavoriteContentPreview() {
    MovieFavoriteContent(
        modifier = Modifier,
        paddingValues = PaddingValues(),
        movies = listOf(
            Movie(
                id = 1,
                title = "Homem Aranha",
                voteAverage = 7.89,
                imageUrl = ""
            ),
            Movie(
                id = 2,
                title = "Homem Ferro",
                voteAverage = 7.89,
                imageUrl = ""
            )
        ),
        onClick = {}
    )
}
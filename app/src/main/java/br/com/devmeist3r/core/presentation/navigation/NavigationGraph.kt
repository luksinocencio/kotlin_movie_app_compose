package br.com.devmeist3r.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.devmeist3r.core.util.Constants
import br.com.devmeist3r.movie_detail_feature.presentation.MovieDetailScreen
import br.com.devmeist3r.movie_detail_feature.presentation.MovieDetailViewModel
import br.com.devmeist3r.movie_favorite_feature.presentation.MovieFavoriteScreen
import br.com.devmeist3r.movie_favorite_feature.presentation.MovieFavoriteViewModel
import br.com.devmeist3r.movie_popular_feature.presentation.MoviePopularScreen
import br.com.devmeist3r.movie_popular_feature.presentation.MoviePopularViewModel
import br.com.devmeist3r.search_movie_feature.presentation.MovieSearchEvent
import br.com.devmeist3r.search_movie_feature.presentation.MovieSearchScreen
import br.com.devmeist3r.search_movie_feature.presentation.MovieSearchViewModel

@Composable
fun NavigationGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = BottomNavItem.MoviePopular.route
    ) {
        composable(
            BottomNavItem.MoviePopular.route
        ) {
            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MoviePopularScreen(
                uiState = uiState,
                navigateToDetailMovie = {
                    navHostController.navigate(BottomNavItem.MovieDetail.passMovieId(movieId = it))
                }
            )
        }
        composable(
            BottomNavItem.MovieSearch.route
        ) {
            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onEvent: (MovieSearchEvent) -> Unit = viewModel::event
            val onFetch: (String) -> Unit = viewModel::fetch

            MovieSearchScreen(
                uiState = uiState,
                onEvent = onEvent,
                onFetch = onFetch,
                navigateToDetailMovie = {
                    navHostController.navigate(BottomNavItem.MovieDetail.passMovieId(movieId = it))
                }
            )
        }

        composable(
            BottomNavItem.MovieFavorites.route
        ) {
            val viewModel: MovieFavoriteViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MovieFavoriteScreen(
                uiState = uiState,
                navigateToDetailMovie = {
                    navHostController.navigate(BottomNavItem.MovieDetail.passMovieId(movieId = it))
                }
            )
        }

        composable(
            route = BottomNavItem.MovieDetail.route,
            arguments = listOf(
                navArgument(Constants.MOVIE_DETAIL_ARGUMENT_KEY) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            val viewModel: MovieDetailViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onAddFavorite = viewModel::onAddFavorite
            val checkedFavorite = viewModel::checkedFavorite
            val getMovieDetail = viewModel::getMovieDetail

            MovieDetailScreen(
                id = it.arguments?.getInt(Constants.MOVIE_DETAIL_ARGUMENT_KEY),
                uiState = uiState,
                onAddFavorite = onAddFavorite,
                checkedFavorite = checkedFavorite,
                getMovieDetail = getMovieDetail
            )
        }
    }
}
package br.com.devmeist3r.movie_favorite_feature.data.repository

import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import br.com.devmeist3r.movie_favorite_feature.domain.source.MovieFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieFavoriteRepositoryImpl @Inject constructor(
    private val localDataSource: MovieFavoriteLocalDataSource
) : MovieFavoriteRepository {
    override fun getMovies(): Flow<List<Movie>> {
        return localDataSource.getMovies()
    }

    override suspend fun insert(movie: Movie) {
        return localDataSource.insert(movie)
    }

    override suspend fun delete(movie: Movie) {
        return localDataSource.delete(movie)
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return localDataSource.isFavorite(movieId)
    }
}
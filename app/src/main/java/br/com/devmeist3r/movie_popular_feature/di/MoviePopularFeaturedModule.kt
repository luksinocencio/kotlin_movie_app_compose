package br.com.devmeist3r.movie_popular_feature.di

import br.com.devmeist3r.core.data.remote.MovieService
import br.com.devmeist3r.movie_popular_feature.data.repository.MoviePopularRepositoryImpl
import br.com.devmeist3r.movie_popular_feature.data.source.MoviePopularRemoteDataSourceImpl
import br.com.devmeist3r.movie_popular_feature.domain.repository.MoviePopularRepository
import br.com.devmeist3r.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import br.com.devmeist3r.movie_popular_feature.domain.usecase.GetPopularMoviesUseCase
import br.com.devmeist3r.movie_popular_feature.domain.usecase.GetPopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviePopularFeaturedModule {
    @Provides
    @Singleton
    fun provideMovieDataSource(service: MovieService): MoviePopularRemoteDataSource {
        return MoviePopularRemoteDataSourceImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(remoteDataSource: MoviePopularRemoteDataSource): MoviePopularRepository {
        return MoviePopularRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMoviesPopularUseCase(moviePopularRepository: MoviePopularRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCaseImpl(repository = moviePopularRepository)
    }
}
package br.com.devmeist3r.movie_detail_feature.di

import br.com.devmeist3r.core.data.remote.MovieService
import br.com.devmeist3r.movie_detail_feature.data.repository.MovieDetailsRepositoryImpl
import br.com.devmeist3r.movie_detail_feature.data.source.MovieDetailsRemoteDataSourceImpl
import br.com.devmeist3r.movie_detail_feature.domain.repository.MovieDetailsRepository
import br.com.devmeist3r.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import br.com.devmeist3r.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import br.com.devmeist3r.movie_detail_feature.domain.usecase.GetMovieDetailsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailFeaturedModule {
    @Provides
    @Singleton
    fun provideMovieDetailsDataSource(service: MovieService): MovieDetailsRemoteDataSource {
        return MovieDetailsRemoteDataSourceImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(remoteDataSource: MovieDetailsRemoteDataSource): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(repository: MovieDetailsRepository): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCaseImpl(repository = repository)
    }
}
package br.com.devmeist3r.di

import android.content.Context
import androidx.room.Room
import br.com.devmeist3r.core.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)

object TestAppModule {
  @Provides
  @Named("test_db")
  fun provideInMemoryDb(@ApplicationContext context: Context) =
    Room.inMemoryDatabaseBuilder(
      context, MovieDatabase::class.java
    ).allowMainThreadQueries().build()
}
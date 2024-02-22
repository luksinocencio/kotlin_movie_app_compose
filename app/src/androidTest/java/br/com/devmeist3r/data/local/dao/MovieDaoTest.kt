package br.com.devmeist3r.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import br.com.devmeist3r.core.data.local.MovieDatabase
import br.com.devmeist3r.core.data.local.dao.MovieDao
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest

class MovieDaoTest {
  @get:Rule
  var hiltRule = HiltAndroidRule(this)

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Inject
  @Named("test_db")
  lateinit var database: MovieDatabase
  private lateinit var movieDao: MovieDao

  @Before
  fun setup() {
    hiltRule.inject()
    movieDao = database.movieDao()
  }

  @After
  fun tearDown() {
    database.close()
  }

  @Test
  fun getMovies() = runTest {
    // Given - nothing

    // When
    val movies = movieDao.getMovies().first()

    // Then
    assertThat(movies.size).isEqualTo(0)
  }
}
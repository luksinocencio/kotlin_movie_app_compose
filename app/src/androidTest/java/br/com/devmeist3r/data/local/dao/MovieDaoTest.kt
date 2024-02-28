package br.com.devmeist3r.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import br.com.devmeist3r.core.data.local.MovieDatabase
import br.com.devmeist3r.core.data.local.dao.MovieDao
import br.com.devmeist3r.core.data.local.entity.MovieEntity
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
  fun test_getMovies_should_return_list_of_movies() = runTest {
    // Given - nothing

    // When
    val movies = movieDao.getMovies().first()

    // Then
    assertThat(movies.size).isEqualTo(0)
  }

  @Test
  fun test_getMovies_should_return_movies_ordered_by_id() = runTest {
    // Given
    val moviesEntities = listOf(
      MovieEntity(movieId = 1, title = "Homem de ferro 1", imageUrl = "any_url_1"),
      MovieEntity(movieId = 2, title = "Homem de ferro 2", imageUrl = "any_url_2"),
      MovieEntity(movieId = 3, title = "Homem de ferro 3", imageUrl = "any_url_3"),
      MovieEntity(movieId = 4, title = "Homem de ferro 4", imageUrl = "any_url_4"),
    )

    movieDao.insertMovies(moviesEntities)

    // When
    val movies = movieDao.getMovies().first()

    // Then
    assertThat(movies.size).isEqualTo(4)
    assertThat(movies[0].movieId).isEqualTo(1)
    assertThat(movies[1].movieId).isEqualTo(2)
    assertThat(movies[2].movieId).isEqualTo(3)
    assertThat(movies[3].movieId).isEqualTo(4)
  }

  @Test
  fun test_getMovies_should_return_correct_movie_by_id() = runTest {
    // Given
    val movieEntity = MovieEntity(movieId = 1, title = "Homem de ferro 1", imageUrl = "any_url_1")
    movieDao.insertMovie(movieEntity)
    val movies = movieDao.getMovies().first()
    val movieClick = movies[0]

    // When
    val movieId = movieDao.getMovie(movieClick.movieId)

    // Then
    assertThat(movieId?.title).isEqualTo(movieClick.title)
  }
}
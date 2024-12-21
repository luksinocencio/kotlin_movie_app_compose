package br.com.devmeist3r.core.paging

import androidx.paging.PagingSource
import br.com.devmeist3r.TestDispatcherRule
import br.com.devmeist3r.core.domain.model.Movie
import br.com.devmeist3r.core.domain.model.MovieFactory
import br.com.devmeist3r.core.domain.model.MoviePagingFactory
import br.com.devmeist3r.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieSimilarPagingSourceTest {
  @get:Rule
  val dispatcherRule = TestDispatcherRule()

  @Mock
  lateinit var remoteDataSource: MovieDetailsRemoteDataSource

  private val movieFactory = MovieFactory()

  private val moviePagingFactory = MoviePagingFactory().create()

  private val moviesSimilarPagingSource by lazy {
    MovieSimilarPagingSource(
      movieId = 1,
      remoteDataSource = remoteDataSource
    )
  }

  @Test
  fun `must return a success load result when load is called`() = runTest {
    // Given
    whenever(remoteDataSource.getMoviesSimilar(any(), any()))
      .thenReturn(moviePagingFactory)

    // When
    val result = moviesSimilarPagingSource.load(
      PagingSource.LoadParams.Refresh(
        key = null,
        loadSize = 2,
        placeholdersEnabled = false
      )
    )

    val resultExpected = listOf(
      movieFactory.create(MovieFactory.Poster.Avengers),
      movieFactory.create(MovieFactory.Poster.JohnWick),
    )

    // Then
    Truth.assertThat(
      PagingSource.LoadResult.Page(
        data = resultExpected,
        prevKey = null,
        nextKey = null
      )
    ).isEqualTo(result)
  }

  @Test
  fun `must return a error load result when load is called`() = runTest {
    // Given
    val exception = RuntimeException()
    whenever(remoteDataSource.getMoviesSimilar(any(), any()))
      .thenThrow(exception)

    // When
    val result = moviesSimilarPagingSource.load(
      PagingSource.LoadParams.Refresh(
        key = null,
        loadSize = 2,
        placeholdersEnabled = false
      )
    )


    // Then
    Truth.assertThat(PagingSource.LoadResult.Error<Int, Movie>(exception)).isEqualTo(result)
  }
}
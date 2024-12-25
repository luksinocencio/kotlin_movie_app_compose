package br.com.devmeist3r.movie_popular_feature.domain.usecase

import androidx.paging.PagingConfig
import br.com.devmeist3r.TestDispatcherRule
import br.com.devmeist3r.core.domain.model.MovieFactory
import br.com.devmeist3r.core.domain.model.PagingSourceMoviesFactory
import br.com.devmeist3r.movie_popular_feature.domain.repository.MoviePopularRepository
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesUseCaseImplTest {
  @get:Rule
  val dispatcherRule = TestDispatcherRule()

  @Mock
  lateinit var moviePopularRepository: MoviePopularRepository

  private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

  private val pagingSourceFake = PagingSourceMoviesFactory().create(
    listOf(movie)
  )

  private val getPopularMoviesUseCase by lazy {
    GetPopularMoviesUseCaseImpl(moviePopularRepository)
  }

  @Test
  fun `should validate flow paging data creation when invoke from use case is called`() = runTest {
    // Given
    whenever(moviePopularRepository.getPopularMovies())
      .thenReturn(pagingSourceFake)

    // When
    val result = getPopularMoviesUseCase.invoke(
      params = GetPopularMoviesUseCase.Params(
        PagingConfig(
          pageSize = 20,
          initialLoadSize = 20
        )
      )
    )

    // Then
    verify(moviePopularRepository).getPopularMovies()
    Truth.assertThat(result).isNotNull()
  }

  @Test
  fun `should emit an empty stream when an exception is thrown when calling the invoke`() =
    runTest {
      // Given
      val exception = RuntimeException()
      whenever(moviePopularRepository.getPopularMovies())
        .thenThrow(exception)

      // When
      val result = getPopularMoviesUseCase.invoke(
        params = GetPopularMoviesUseCase.Params(
          PagingConfig(
            pageSize = 20,
            initialLoadSize = 20
          )
        )
      )

      // Then
      val resultList = result.toList()
      verify(moviePopularRepository).getPopularMovies()
      Truth.assertThat(resultList).isEmpty()
    }
}
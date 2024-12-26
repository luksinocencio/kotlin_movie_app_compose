package br.com.devmeist3r.movie_detail_feature.domain.usecase

import androidx.paging.PagingConfig
import br.com.devmeist3r.TestDispatcherRule
import br.com.devmeist3r.core.domain.model.MovieDetailsFactory
import br.com.devmeist3r.core.domain.model.MovieFactory
import br.com.devmeist3r.core.domain.model.PagingSourceMoviesFactory
import br.com.devmeist3r.core.util.ResultData
import br.com.devmeist3r.movie_detail_feature.domain.repository.MovieDetailsRepository
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.verify
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
class GetMovieDetailsUseCaseImplTest {
  @get:Rule
  val dispatcherRule = TestDispatcherRule()

  @Mock
  private lateinit var movieDetailRepository: MovieDetailsRepository

  private val movieFactory = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

  private val movieDetailFactory = MovieDetailsFactory()
    .create(poster = MovieDetailsFactory.Poster.Avengers)

  private val pagingSourceFake = PagingSourceMoviesFactory()
    .create(
      listOf(movieFactory)
    )

  private val getMovieDetailsUseCase by lazy {
    GetMovieDetailsUseCaseImpl(movieDetailRepository)
  }

  @Test
  fun `should return Success from ResultsStatus when get both requests return success`() = runTest {
    // Given
    whenever(movieDetailRepository.getMovieDetails(movieId = movieFactory.id))
      .thenReturn(movieDetailFactory)

    whenever(movieDetailRepository.getMoviesSimilar(movieId = movieFactory.id)).thenReturn(
      pagingSourceFake
    )

    // When
    val result = getMovieDetailsUseCase.invoke(
      GetMovieDetailsUseCase.Params(
        movieId = movieFactory.id,
        pagingConfig = PagingConfig(
          pageSize = 20,
          initialLoadSize = 20
        )
      )
    )

    // Then
    verify(movieDetailRepository).getMovieDetails(movieFactory.id)
    verify(movieDetailRepository).getMoviesSimilar(movieFactory.id)

    Truth.assertThat(result).isNotNull()
    Truth.assertThat(result is ResultData.Success).isTrue()
  }

  @Test
  fun `should return Error from ResultStatus when get movieDetails request returns error`() =
    runTest {
      // Given
      val exception = RuntimeException()
      whenever(movieDetailRepository.getMovieDetails(movieFactory.id))
        .thenThrow(exception)

      // When
      val result = getMovieDetailsUseCase.invoke(
        GetMovieDetailsUseCase.Params(
          movieId = movieFactory.id,
          pagingConfig = PagingConfig(
            pageSize = 20,
            initialLoadSize = 20
          )
        )
      )

      // Then
      verify(movieDetailRepository).getMovieDetails(movieFactory.id)
      Truth.assertThat(result is ResultData.Failure).isTrue()
    }

  @Test
  fun `should return a ResultStatus error when getting similar movies returns an error`() =
    runTest {
      // Given
      val exception = RuntimeException()
      whenever(movieDetailRepository.getMovieDetails(movieFactory.id))
        .thenThrow(exception)

      // When
      val result = getMovieDetailsUseCase.invoke(
        GetMovieDetailsUseCase.Params(
          movieId = movieFactory.id,
          pagingConfig = PagingConfig(
            pageSize = 20,
            initialLoadSize = 20
          )
        )
      )

      // Then
      verify(movieDetailRepository).getMoviesSimilar(movieFactory.id)
      Truth.assertThat(result is ResultData.Failure).isTrue()
    }
}
package br.com.devmeist3r.movie_detail_feature.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import br.com.devmeist3r.TestDispatcherRule
import br.com.devmeist3r.core.domain.model.MovieDetailsFactory
import br.com.devmeist3r.core.domain.model.MovieFactory
import br.com.devmeist3r.core.util.ResultData
import br.com.devmeist3r.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import br.com.devmeist3r.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.devmeist3r.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.devmeist3r.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {
  @get:Rule
  val dispatcherRule = TestDispatcherRule()

  @Mock
  lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

  @Mock
  lateinit var addMovieFavoriteUseCase: AddMovieFavoriteUseCase

  @Mock
  lateinit var deleteMovieDetailsUseCase: DeleteMovieFavoriteUseCase

  @Mock
  lateinit var isFavoriteMovieUseCase: IsMovieFavoriteUseCase

  @Mock
  lateinit var savedStateHandle: SavedStateHandle

  private val movieDetailsFactory = MovieDetailsFactory().create(poster = MovieDetailsFactory.Poster.Avengers)

  private val pagingData = PagingData.from(
    listOf(
      MovieFactory().create(poster = MovieFactory.Poster.Avengers),
      MovieFactory().create(poster = MovieFactory.Poster.JohnWick),
    )
  )

  private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

  private val viewModel by lazy {
    MovieDetailViewModel(
      getMovieDetailsUseCase = getMovieDetailsUseCase,
      addMovieFavoriteUseCase = addMovieFavoriteUseCase,
      deleteMovieFavoriteUseCase = deleteMovieDetailsUseCase,
      isMovieFavoriteUseCase = isFavoriteMovieUseCase,
      savedStateHandle = savedStateHandle.apply {
        whenever(savedStateHandle.get<Int>("movieId")).thenReturn(movie.id)
      }
    )
  }

  @Test
  fun `must notify uiState with Success when get movies similar and movie details returns success`() = runTest {
    // Given
    whenever(getMovieDetailsUseCase.invoke(any()))
      .thenReturn(flowOf(ResultData.Success(flowOf(pagingData) to movieDetailsFactory)))

    val argumentCaptor = argumentCaptor<GetMovieDetailsUseCase.Params>()

    // When
    viewModel.uiState.isLoading

    // Then
    verify(getMovieDetailsUseCase).invoke(argumentCaptor.capture())
    assertThat(movieDetailsFactory.id).isEqualTo(argumentCaptor.firstValue.movieId)

    val movieDetails = viewModel.uiState.movieDetails
    val results = viewModel.uiState.results
    assertThat(movieDetails).isNotNull()
    assertThat(results).isNotNull()
  }
}
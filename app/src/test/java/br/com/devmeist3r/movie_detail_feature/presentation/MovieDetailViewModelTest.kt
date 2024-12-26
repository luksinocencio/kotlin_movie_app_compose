package br.com.devmeist3r.movie_detail_feature.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import br.com.devmeist3r.TestDispatcherRule
import br.com.devmeist3r.core.domain.model.MovieDetailsFactory
import br.com.devmeist3r.core.domain.model.MovieFactory
import br.com.devmeist3r.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import br.com.devmeist3r.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.devmeist3r.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.devmeist3r.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
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

  private val movieDetailsFactory = MovieDetailsFactory().create(
    poster = MovieDetailsFactory.Poster.Avengers
  )

  private val pagingData = PagingData.from(
    listOf(
      MovieFactory().create(poster = MovieFactory.Poster.Avengers),
      MovieFactory().create(poster = MovieFactory.Poster.JohnWick),
    )
  )

  private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

  private val viewModel by lazy {
    MovieDetailViewModel(getMovieDetailsUseCase = getMovieDetailsUseCase,
      addMovieFavoriteUseCase = addMovieFavoriteUseCase,
      deleteMovieFavoriteUseCase = deleteMovieDetailsUseCase,
      isMovieFavoriteUseCase = isFavoriteMovieUseCase,
      savedStateHandle = savedStateHandle.apply {
        whenever(savedStateHandle.get<Int>("movieId")).thenReturn(movie.id)
      })
  }
  /*

  @Test
  fun `must notify uiState with Success when get movies similar and movie details returns success`() =
    runTest {
      // Given
      whenever(getMovieDetailsUseCase.invoke(any())).thenReturn(
        flowOf(
          ResultData.Success(
            flowOf(
              pagingData
            ) to movieDetailsFactory
          )
        )
      )

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

  @Test
  fun `must notify uiState with Failure when get movies details and returns exception`() = runTest {
    // Given
    val exception = Exception("Um erro ocorreu!")
    whenever(getMovieDetailsUseCase.invoke(any())).thenReturn(flowOf(ResultData.Failure(exception)))

    // When
    viewModel.uiState.isLoading

    // Then
    val error = viewModel.uiState.error
    assertThat(exception.message).isEqualTo(error)
  }

  @Test
  fun `must call delete favorite and notify of uiState with filled favorite icon when current icon is checked`() =
    runTest {
      // Given
      whenever(deleteMovieDetailsUseCase.invoke(any())).thenReturn(flowOf(ResultData.Success(Unit)))

      whenever(isFavoriteMovieUseCase.invoke(any())).thenReturn(flowOf(ResultData.Success(true)))

      val deleteArgumentCaptor = argumentCaptor<DeleteMovieFavoriteUseCase.Params>()
      val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

      // When
      viewModel.onAddFavorite(movie = movie)

      // Then
      verify(deleteMovieDetailsUseCase).invoke(deleteArgumentCaptor.capture())
      assertThat(movie).isEqualTo(deleteArgumentCaptor.firstValue.movie)

      verify(isFavoriteMovieUseCase).invoke(checkedArgumentCaptor.capture())
      assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

      val iconColor = viewModel.uiState.iconColor
      assertThat(Color.White).isEqualTo(iconColor)
    }

  @Test
  fun `must notify uiState with filled favorite icon when current icon is unchecked`() = runTest {
    // Given
    whenever(addMovieFavoriteUseCase.invoke(any())).thenReturn(flowOf(ResultData.Success(Unit)))

    whenever(isFavoriteMovieUseCase.invoke(any())).thenReturn(flowOf(ResultData.Success(false)))

    val addArgumentCaptor = argumentCaptor<AddMovieFavoriteUseCase.Params>()
    val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

    // When
    viewModel.onAddFavorite(movie = movie)

    // Then
    verify(addMovieFavoriteUseCase).invoke(addArgumentCaptor.capture())
    assertThat(movie).isEqualTo(addArgumentCaptor.firstValue.movie)

    verify(isFavoriteMovieUseCase).invoke(checkedArgumentCaptor.capture())
    assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

    val iconColor = viewModel.uiState.iconColor
    assertThat(Color.Red).isEqualTo(iconColor)
  }

  @Test
  fun `must notify uiState with bookmark icon filled in if bookmark check returns true`() =
    runTest {
      // Given
      whenever(isFavoriteMovieUseCase.invoke(any()))
        .thenReturn(flowOf(ResultData.Success(true)))

      val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

      // When
      viewModel.uiState.isLoading

      // Then

      verify(isFavoriteMovieUseCase).invoke(checkedArgumentCaptor.capture())
      assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

      val iconColor = viewModel.uiState.iconColor
      assertThat(Color.Red).isEqualTo(iconColor)
    }

  @Test
  fun `must notify uiState with bookmark icon filled in if bookmark check returns false`() =
    runTest {
      // Given
      whenever(isFavoriteMovieUseCase.invoke(any()))
        .thenReturn(flowOf(ResultData.Success(false)))

      val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

      // When
      viewModel.uiState.isLoading

      // Then

      verify(isFavoriteMovieUseCase).invoke(checkedArgumentCaptor.capture())
      assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

      val iconColor = viewModel.uiState.iconColor
      assertThat(Color.White).isEqualTo(iconColor)
    }
   */
}
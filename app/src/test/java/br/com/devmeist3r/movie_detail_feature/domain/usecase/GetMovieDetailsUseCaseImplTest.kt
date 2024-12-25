package br.com.devmeist3r.movie_detail_feature.domain.usecase

import br.com.devmeist3r.TestDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailsUseCaseImplTest {
  @get:Rule
  val dispatcherRule = TestDispatcherRule()

}
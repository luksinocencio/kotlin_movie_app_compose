package br.com.devmeist3r.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.devmeist3r.core.domain.model.MovieSearch
import br.com.devmeist3r.search_movie_feature.domain.source.MovieSearchRemoteDataSource


class MovieSearchPagingSource(
  private val query: String,
  private val remoteDataSource: MovieSearchRemoteDataSource
) : PagingSource<Int, MovieSearch>() {
  override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      val anchorPage = state.closestPageToPosition(anchorPosition)
      anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> {
    return try {
      val pageNumber = params.key ?: 1
      val response = remoteDataSource.getSearchMovies(page = pageNumber, query = query)
      val movies = response.movies
      val totalPages = response.totalPages

      LoadResult.Page(
        data = movies,
        prevKey = if (pageNumber == 1) null else pageNumber - 1,
        nextKey = if (pageNumber == totalPages) null else pageNumber + 1
      )
    } catch (exception: Exception) {
      LoadResult.Error(exception)
    }

  }

  companion object {
    private const val LIMIT = 20
  }
}
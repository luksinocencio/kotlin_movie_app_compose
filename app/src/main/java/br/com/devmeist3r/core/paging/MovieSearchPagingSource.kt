package br.com.devmeist3r.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.*
import androidx.paging.PagingState
import br.com.devmeist3r.core.domain.model.MovieSearch
import br.com.devmeist3r.movie_popular_feature.data.mapper.toMovie
import br.com.devmeist3r.search_movie_feature.data.mapper.toMovieSearch
import br.com.devmeist3r.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import okio.IOException
import retrofit2.HttpException


class MovieSearchPagingSource(
    private val query: String,
    private val remoteDataSource: MovieSearchRemoteDataSource
): PagingSource<Int, MovieSearch>() {
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
            val movies = response.results

            LoadResult.Page(
                data = movies.toMovieSearch(),
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (movies.isEmpty()) null else pageNumber + 1
            )
        } catch (exception: IOException) {
            exception.printStackTrace()
            return Error(exception)
        } catch (exception: HttpException) {
            exception.printStackTrace()
            return Error(exception)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}
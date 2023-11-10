package br.com.devmeist3r.core.data.remote.response

data class SearchResult(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
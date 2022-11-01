package uz.gita.moviesapp.data.sources.common.models

data class MoviesData(
    val page: Int,
    val results: List<Results>,
    val total_page: Int,
    val total_result: Int
)
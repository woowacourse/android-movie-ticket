package woowacourse.movie.domain

data class Movie(
    val poster: Int,
    val title: String,
    val date: Date,
    val time: Int,
    val description: String,
)

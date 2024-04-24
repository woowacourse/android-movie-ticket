package woowacourse.movie.model

data class Movie(
    val id: Int,
    val title: String,
    val thumbnail: Int,
    val date: MovieDate,
    val runningTime: Int,
    val introduction: String,
)

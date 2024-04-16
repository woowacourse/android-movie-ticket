package woowacourse.movie.model

data class Movie(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val date: String,
    val runningTime: Int,
    val introduction: String,
)

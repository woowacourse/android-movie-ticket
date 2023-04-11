package woowacourse.movie.domain

data class Movie(
    val image: Int,
    val title: String,
    val playingDate: String,
    val runningTime: Int,
    val description: String
) : java.io.Serializable

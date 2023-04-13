package woowacourse.movie.model

data class Movie(
    val image: Int,
    val title: String,
    val playingTimes: PlayingTimes,
    val runningTime: Int,
    val description: String
) : java.io.Serializable

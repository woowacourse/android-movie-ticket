package woowacourse.movie.model

import androidx.annotation.DrawableRes

data class MovieModel(
    @DrawableRes val image: Int,
    val title: String,
    val playingTimes: PlayingTimesModel,
    val runningTime: Int,
    val description: String
) : java.io.Serializable

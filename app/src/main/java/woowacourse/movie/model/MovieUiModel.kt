package woowacourse.movie.model

import androidx.annotation.DrawableRes

data class MovieUiModel(
    @DrawableRes val image: Int,
    val title: String,
    val playingTimes: com.example.domain.model.PlayingTimes,
    val runningTime: Int,
    val description: String
) : java.io.Serializable

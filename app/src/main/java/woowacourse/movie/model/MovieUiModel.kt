package woowacourse.movie.model

import androidx.annotation.DrawableRes
import com.example.domain.model.PlayingTimes

data class MovieUiModel(
    @DrawableRes val image: Int,
    val title: String,
    val playingTimes: PlayingTimes,
    val runningTime: Int,
    val description: String
)

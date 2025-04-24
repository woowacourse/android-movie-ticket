package woowacourse.movie.presentation.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieUiModel(
    val title: String = "",
    val startDate: MovieDateUiModel = MovieDateUiModel(),
    val endDate: MovieDateUiModel = MovieDateUiModel(),
    val runningTime: Int = 0,
    @DrawableRes val poster: Int = 0,
) : Parcelable

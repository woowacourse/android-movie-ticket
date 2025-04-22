package woowacourse.movie.ui

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieUiModel(
    @DrawableRes val imageSource: Int,
    val title: String,
    val screeningPeriod: String,
    val runningTimeText: String,
) : Parcelable

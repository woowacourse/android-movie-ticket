package woowacourse.movie.uiModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieInfoUIModel(
    val posterKey: String,
    val title: String,
    val startDate: String,
    val endDate: String,
    val runningTime: Int,
) : Parcelable

package woowacourse.movie.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieInfo(
    val poster: Int,
    val title: String,
    val startDate: String,
    val endDate: String,
    val runningTime: Int,
) : Parcelable

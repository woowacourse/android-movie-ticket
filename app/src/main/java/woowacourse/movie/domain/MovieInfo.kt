package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.ranges.step

@Parcelize
data class MovieInfo(
    val poster: Int,
    val title: String,
    val startDate: String,
    val endDate: String,
    val runningTime: String,
) : Parcelable
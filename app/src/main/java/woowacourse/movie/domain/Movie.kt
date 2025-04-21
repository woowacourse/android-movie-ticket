package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.Duration
import java.time.LocalDate

@Parcelize
data class Movie(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Duration,
) : Parcelable
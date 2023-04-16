package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Movie(
    val poster: Int,
    val title: String,
    val releaseDate: String,
    val runningTime: String,
    val synopsis: String,
    val startDate: LocalDate,
    val endDate: LocalDate
) : Parcelable

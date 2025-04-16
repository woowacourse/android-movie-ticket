package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Movie(
    val title: String,
    val screeningDate: LocalDate,
    val runningTime: Int,
    val poster: Int,
) : Parcelable

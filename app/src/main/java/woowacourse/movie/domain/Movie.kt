package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Movie(
    val id: Long,
    val title: String,
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
    val runningTime: Int,
    val description: String,
    val thumbnail: Int,
    val poster: Int,
) : Parcelable

package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import kotlin.time.Duration

@Parcelize
data class Movie(
    val title: String,
    val posterUrl: Int,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val runningTime: Duration,
) : Parcelable

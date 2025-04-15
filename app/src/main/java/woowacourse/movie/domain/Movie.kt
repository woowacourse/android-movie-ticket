package woowacourse.movie.domain

import java.time.LocalDateTime
import kotlin.time.Duration

data class Movie(
    val title: String,
    val posterUrl: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val runningTime: Duration
)

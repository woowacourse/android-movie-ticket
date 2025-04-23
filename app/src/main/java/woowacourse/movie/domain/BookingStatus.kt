package woowacourse.movie.domain

import java.time.LocalDateTime

data class BookingStatus(
    val movie: Movie,
    val isBooked: Boolean,
    val memberCount: MemberCount,
    val bookedTime: LocalDateTime,
)

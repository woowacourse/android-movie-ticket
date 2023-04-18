package woowacourse.movie.view

import java.time.LocalDateTime

data class ReservationInfo(val movieId: Long, val screeningDateTime: LocalDateTime): java.io.Serializable

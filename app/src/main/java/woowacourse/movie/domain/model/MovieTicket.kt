package woowacourse.movie.domain.model

import java.io.Serializable
import java.time.LocalDateTime

data class MovieTicket(
    val movieId: Int,
    val screeningDateTime: LocalDateTime,
    val headCount: Int,
    var amount: Int = 0,
    val selectedSeats: MutableList<String> = mutableListOf(),
) : Serializable

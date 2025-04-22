package woowacourse.movie.domain.model

import java.io.Serializable
import java.time.LocalDateTime

data class MovieTicket(
    val movieId: String,
    val screeningDateTime: LocalDateTime,
    val headCount: Int,
    val amount: Int,
) : Serializable

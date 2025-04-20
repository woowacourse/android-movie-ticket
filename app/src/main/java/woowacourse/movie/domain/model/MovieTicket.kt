package woowacourse.movie.domain.model

import java.io.Serializable
import java.time.LocalDateTime

data class MovieTicket(
    val title: String,
    val screeningDateTime: LocalDateTime,
    val headCount: Int,
    val amount: Int,
) : Serializable

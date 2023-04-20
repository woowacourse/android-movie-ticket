package woowacourse.movie.model

import java.time.LocalDateTime

data class TicketModel(
    val title: String,
    val playingDateTime: LocalDateTime,
    val count: Int,
    val price: Int
) : java.io.Serializable

package woowacourse.movie.domain.ticket

import woowacourse.movie.domain.price.Price
import woowacourse.movie.domain.seat.Seat
import java.time.LocalDateTime

data class Ticket(
    val title: String,
    val playingDateTime: LocalDateTime,
    val seats: List<Seat>,
    val price: Price,
)

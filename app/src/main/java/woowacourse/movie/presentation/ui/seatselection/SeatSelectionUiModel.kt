package woowacourse.movie.presentation.ui.seatselection

import woowacourse.movie.domain.model.Seat
import java.time.LocalDateTime

data class SeatSelectionUiModel(
    val id: Int = -1,
    val dateTime: LocalDateTime? = null,
    val ticketCount: Int = 0,
    val seats: MutableSet<Seat> = mutableSetOf(),
    val totalPrice: Int = 0,
)

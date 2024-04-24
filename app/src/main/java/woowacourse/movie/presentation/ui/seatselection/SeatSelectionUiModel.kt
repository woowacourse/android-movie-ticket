package woowacourse.movie.presentation.ui.seatselection

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.presentation.model.UserSeat
import java.time.LocalDateTime

data class SeatSelectionUiModel(
    val id: Int = -1,
    val screen: Screen? = null,
    val dateTime: LocalDateTime? = null,
    val ticketCount: Int = 0,
    val userSeat: UserSeat = UserSeat(emptyList()),
    val totalPrice: Int = 0,
)

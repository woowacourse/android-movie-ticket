package woowacourse.movie.presentation.ui.detail

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.ScreenDate
import woowacourse.movie.presentation.model.Ticket
import java.time.LocalTime

data class DetailUiModel(
    val screenId: Int = -1,
    val screen: Screen? = null,
    val ticket: Ticket = Ticket(Ticket.MIN_TICKET_COUNT),
    val selectableDates: List<ScreenDate> = emptyList(),
    val selectedDate: ScreenDate? = null,
    val selectedTime: LocalTime? = null,
)

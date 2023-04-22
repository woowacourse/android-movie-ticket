package woowacourse.movie.view.model

import java.io.Serializable
import java.time.LocalDateTime

data class TicketViewModel(
    val date: LocalDateTime,
    val seat: SeatViewModel
) : ViewModel, Serializable

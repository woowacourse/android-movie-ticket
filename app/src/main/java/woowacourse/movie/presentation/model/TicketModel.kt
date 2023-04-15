package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.model.Ticket
import java.time.LocalDateTime

@Parcelize
data class TicketModel(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val count: Int,
) : Parcelable {

    fun toTicket() = Ticket(
        movieId = movieId,
        bookedDateTime = bookedDateTime,
        count = count
    )
}

fun Ticket.toPresentation() = TicketModel(
    movieId = movieId,
    bookedDateTime = bookedDateTime,
    count = count
)

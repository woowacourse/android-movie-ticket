package woowacourse.movie.view.reservation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.Seats
import woowacourse.movie.domain.Ticket
import woowacourse.movie.view.movie.model.MovieUiModel
import woowacourse.movie.view.movie.model.toDomain
import java.time.LocalDateTime

@Parcelize
data class TicketUiModel(
    val movie: MovieUiModel,
    val showtime: LocalDateTime,
    val count: Int,
    val seats: Seats,
) : Parcelable {
    companion object {
        fun from(movie: MovieUiModel): TicketUiModel = Ticket(movie.toDomain()).toUiModel()
    }
}

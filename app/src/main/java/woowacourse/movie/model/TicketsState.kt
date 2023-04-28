package woowacourse.movie.model

import android.os.Parcelable
import com.example.domain.model.Tickets
import kotlinx.parcelize.Parcelize
import woowacourse.movie.model.mapper.asDomain
import woowacourse.movie.model.mapper.asPresentation
import java.time.LocalDateTime

@Parcelize
data class TicketsState(
    val movieState: MovieState,
    val dateTime: LocalDateTime,
    val positions: List<SeatPositionState>
) : Parcelable {

    companion object {
        fun from(reservationState: ReservationState, seats: List<SeatPositionState>): TicketsState {
            return Tickets.from(reservationState.asDomain(), seats.map { it.asDomain() }).asPresentation()
        }
    }
}

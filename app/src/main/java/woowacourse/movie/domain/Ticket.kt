package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.view.movie.model.MovieUiModel
import java.time.LocalDateTime
import java.time.LocalTime

@Parcelize
data class Ticket(
    val movie: MovieUiModel,
    val showtime: LocalDateTime = LocalDateTime.of(movie.startDate, LocalTime.MIDNIGHT),
    val count: Int = MINIMUM_TICKET_COUNT,
) : Parcelable {
    init {
        require(count >= MINIMUM_TICKET_COUNT)
    }

    fun isMinimumCount(): Boolean = count == MINIMUM_TICKET_COUNT

    fun totalPrice(): Int = count * TICKET_PRICE

    companion object {
        private const val MINIMUM_TICKET_COUNT = 1
        private const val TICKET_PRICE = 13_000
    }
}

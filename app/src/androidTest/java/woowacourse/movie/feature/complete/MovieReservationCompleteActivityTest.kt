package woowacourse.movie.feature.complete

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.feature.FIRST_MOVIE_ID
import woowacourse.movie.feature.equalText
import woowacourse.movie.feature.firstMovie
import woowacourse.movie.feature.view
import woowacourse.movie.model.data.TicketRepositoryImpl
import woowacourse.movie.model.reservation.ReservationCount
import woowacourse.movie.model.reservation.Ticket
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.seat.SelectedSeats
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class MovieReservationCompleteActivityTest {
    private val ticketId = 0L
    private val ticket = TicketRepositoryImpl.find(ticketId)
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieReservationCompleteActivity::class.java,
        ).apply {
            putExtra("ticket_id_key", ticketId)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieReservationCompleteActivity>(intent)

    @Test
    fun `화면이_띄워지면_영화_제목이_보인다`() {
        view(R.id.title_text)
            .equalText(firstMovie.title)
    }

    @Test
    fun `화면이_띄워지면_상영_시간이_보인다`() {
        view(R.id.screening_date_time_text)
            .equalText("2024.4.20 09:00")
    }

    @Test
    fun `화면이_띄워지면_예매_인원과_예매_좌석이_보인다`() {
        view(R.id.seats_info_text)
            .equalText("일반 3명 | A2, C3, E1")
    }

    @Test
    fun `화면이_띄워지면_예매_금액이_보인다`() {
        view(R.id.reservation_amount_text)
            .equalText("%,d원 (현장 결제)".format(ticket.amount().amount))
    }

    companion object {
        @JvmStatic
        @BeforeClass
        fun setUp() {
            val selectedSeats =
                SelectedSeats(ReservationCount(3)).apply {
                    select(Seat(1, 2)) // A2
                    select(Seat(3, 3)) // C3
                    select(Seat(5, 1)) // E1
                }
            val ticket =
                Ticket(
                    FIRST_MOVIE_ID,
                    LocalDateTime.of(2024, 4, 20, 9, 0),
                    selectedSeats,
                )
            TicketRepositoryImpl.save(ticket)
        }
    }
}

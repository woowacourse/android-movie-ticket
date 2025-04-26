package woowacourse.movie.presentation.view.reservation.result

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.domain.model.cinema.screen.Seat
import woowacourse.movie.domain.model.cinema.screen.SeatType
import woowacourse.movie.domain.model.cinema.ticket.Ticket
import woowacourse.movie.domain.model.cinema.ticket.TicketBundle
import woowacourse.movie.presentation.fixture.fakeContext
import woowacourse.movie.presentation.model.toUiModel
import java.time.LocalDateTime

class ReservationResultActivityTest {
    private val ticketBundle =
        TicketBundle(
            listOf(
                Ticket(
                    "해리 포터와 마법사의 돌",
                    LocalDateTime.of(2025, 4, 15, 11, 0),
                    Seat(0, 1, SeatType.B_CLASS),
                    10_000,
                ),
                Ticket(
                    "해리 포터와 마법사의 돌",
                    LocalDateTime.of(2025, 4, 15, 11, 0),
                    Seat(0, 2, SeatType.B_CLASS),
                    10_000,
                ),
            ),
        ).toUiModel()

    @Before
    fun setUp() {
        val intent = ReservationResultActivity.newIntent(fakeContext, ticketBundle)
        ActivityScenario.launch<ReservationResultActivity>(intent)
    }

    @Test
    fun `예매_취소_가능_시간을_보여준다`() {
        onView(withId(R.id.tv_cancel_description))
            .check(matches(withText("영화 상영 시작 시간 15분 전까지\n취소가 가능합니다.")))
    }

    @Test
    fun `예매한_영화의_제목을_보여준다`() {
        onView(withId(R.id.tv_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `예매한_영화의_상영일을_보여준다`() {
        onView(withId(R.id.tv_movie_date))
            .check(matches(withText("2025.4.15 11:00")))
    }

    @Test
    fun `예매한_영화_예매_인원_수와_좌석_번호를_보여준다`() {
        onView(withId(R.id.tv_reservation_count_info))
            .check(matches(withText("일반 2명 | A1, A2")))
    }

    @Test
    fun `예매한_영화의_인원수에_맞는_총_티켓_가격을_보여준다`() {
        onView(withId(R.id.tv_reservation_total_price))
            .check(matches(withText("20,000원 (현장 결제)")))
    }
}

package woowacourse.movie.ui.ticketingresult

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.model.ReservationUI
import woowacourse.movie.model.TicketUI
import woowacourse.movie.model.TicketsUI
import woowacourse.movie.model.seat.ColUI
import woowacourse.movie.model.seat.RowUI
import woowacourse.movie.model.seat.SeatPositionUI
import woowacourse.movie.ui.movielist.data.MovieRepository
import woowacourse.movie.ui.util.checkMatches
import java.time.LocalDateTime

class TicketingResultActivityTest {
    private val tickets = TicketsUI(
        setOf(
            TicketUI(SeatPositionUI(RowUI(0), ColUI(0))),
            TicketUI(SeatPositionUI(RowUI(1), ColUI(0))),
        )
    )

    // 해리포터
    private val reservation = ReservationUI(
        MovieRepository.allMovies()[0],
        LocalDateTime.of(2023, 4, 21, 12, 0),
        tickets
    )

    private val intent = TicketingResultActivity.getIntent(
        ApplicationProvider.getApplicationContext(),
        reservation
    )

    @get:Rule
    val activityRule = ActivityScenarioRule<TicketingResultActivity>(intent)

    @Test
    fun `이전_화면에서_예매한_해리포터가_예매_영화_제목으로_보여진다`() {
        onView(withId(R.id.tv_title))
            .checkMatches(withText(reservation.movie.title))
    }

    @Test
    fun `이전_화면에서_예매한_예매_일자가_보여진다`() {
        onView(withId(R.id.tv_date))
            .checkMatches(withText("2023.04.21"))
    }

    @Test
    fun `이전_화면에서_예매한_예매_시간이_보여진다`() {
        onView(withId(R.id.tv_time))
            .checkMatches(withText("12:00"))
    }

    @Test
    fun `이전_화면에서_예매한_A1_B1_좌석이_보여진다`() {
        onView(withId(R.id.tv_regular_count))
            .checkMatches(withText("일반 2명 | A1, B1"))
    }

    @Test
    fun `이전_화면에서_예매한_B등급_2장의_가격이_보여진다`() {
        onView(withId(R.id.tv_pay_result))
            .checkMatches(withText("20,000원 (현장 결제)"))
    }
}

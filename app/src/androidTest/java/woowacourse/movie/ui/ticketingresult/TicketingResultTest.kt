package woowacourse.movie.ui.ticketingresult

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.woowacourse.movie.domain.Movie
import com.woowacourse.movie.domain.policy.DiscountDecorator
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.model.ReservationUI
import woowacourse.movie.model.TicketCountUI
import woowacourse.movie.model.TicketUI
import woowacourse.movie.model.TicketsUI
import woowacourse.movie.model.mapper.toMovieUI
import woowacourse.movie.model.mapper.toTickets
import woowacourse.movie.model.seat.ColUI
import woowacourse.movie.model.seat.RowUI
import woowacourse.movie.model.seat.SeatPositionUI
import java.time.LocalDateTime

class TicketingResultTest {
    // 해리포터
    private val reservation = ReservationUI(
        Movie.provideDummy()[0].toMovieUI(),
        LocalDateTime.of(2023, 4, 21, 12, 0),
        TicketCountUI(2)
    )

    private val tickets = TicketsUI(
        listOf(
            TicketUI(SeatPositionUI(RowUI(0), ColUI(0))),
            TicketUI(SeatPositionUI(RowUI(1), ColUI(0))),
        ),
        reservation
    )

    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            TicketingResultActivity::class.java
        ).apply {
            putExtra(TICKETS_KEY, tickets)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<TicketingResultActivity>(intent)
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun `이전_화면에서_예매한_해리포터가_예매_영화_제목으로_보여진다`() {
        onView(withId(R.id.tv_title))
            .check(matches(withText(tickets.reservation.movie.title)))
    }

    @Test
    fun `이전_화면에서_예매한_예매_일자_및_시간이_보여진다`() {
        val dateTime = tickets.reservation.dateTime
        onView(withId(R.id.tv_date))
            .check(
                matches(
                    withText(
                        context.getString(
                            R.string.book_date_time,
                            dateTime.year,
                            dateTime.monthValue,
                            dateTime.dayOfMonth,
                            dateTime.hour,
                            dateTime.minute
                        )
                    )
                )
            )
    }

    @Test
    fun `이전_화면에서_예매한_A1_B1_좌석이_보여진다`() {
        onView(withId(R.id.tv_regular_count))
            .check(
                matches(
                    withText(
                        context.getString(
                            R.string.regular_count,
                            tickets.toTickets().size,
                            tickets.getSeatPositionUIFormat()
                        )
                    )
                )
            )
    }

    @Test
    fun `이전_화면에서_예매한_B등급_2장의_가격이_보여진다`() {
        val decorator = DiscountDecorator(tickets.reservation.dateTime)

        onView(withId(R.id.tv_pay_result))
            .check(
                matches(
                    withText(
                        context.getString(
                            R.string.movie_pay_result,
                            tickets.toTickets().calculatePrice(decorator),
                            context.getString(R.string.on_site_payment)
                        )
                    )
                )
            )
    }

    companion object {
        private const val TICKETS_KEY = "tickets"
    }
}

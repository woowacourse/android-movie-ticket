package woowacourse.movie.ui.complete

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.model.data.UserTicketsImpl
import woowacourse.movie.model.movie.ReservationDetail
import woowacourse.movie.model.movie.UserTicket
import woowacourse.movie.ui.selection.MovieSeatSelectionKey
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class MovieReservationCompleteActivityTest {
    private val userTicket: UserTicket = UserTicketsImpl.find(0L)

    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieReservationCompleteActivity::class.java,
        ).run {
            putExtra(MovieSeatSelectionKey.TICKET_ID, 0L)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieReservationCompleteActivity>(intent)

    @Test
    fun `화면이_띄워지면_영화_제목이_보인다`() {
        onView(withId(R.id.title_text))
            .check(matches(isDisplayed()))
            .check(matches(withText(userTicket.title)))
    }

    @Test
    fun `화면이_띄워지면_상영일이_보인다`() {
        onView(withId(R.id.screening_date_text))
            .check(matches(isDisplayed()))
            .check(matches(withText("2024-03-28")))
    }

    @Test
    fun `화면이_띄워지면_상영시간이_보인다`() {
        onView(withId(R.id.screening_time_text))
            .check(matches(isDisplayed()))
            .check(matches(withText("21:00")))
    }

    @Test
    fun `화면이_띄워지면_예매_인원이_1인_경우_예매_인원의_수가_보인다`() {
        onView(withId(R.id.reservation_count_text))
            .check(matches(isDisplayed()))
            .check(matches(withText("일반 ${RESERVATION_COUNT}명")))
    }

    @Test
    fun `화면이_띄워지면_예매한_좌석번호가_보인다`() {
        onView(withId(R.id.reservation_seat_text))
            .check(matches(isDisplayed()))
            .check(matches(withText("A1")))
    }

    @Test
    fun `화면이_띄워지면_예매_금액이_보인다`() {
        val reservationAmount = userTicket.reservationDetail.totalSeatAmount()

        onView(withId(R.id.reservation_amount_text))
            .check(matches(isDisplayed()))
            .check(matches(withText("%,d원 (현장 결제)".format(reservationAmount))))
    }

    companion object {
        private const val RESERVATION_COUNT = 1

        @JvmStatic
        @BeforeClass
        fun setUp() {
            val reservationDetail =
                ReservationDetail(RESERVATION_COUNT).apply {
                    addSeat(0, 0) // A1
                }
            UserTicketsImpl.save(
                UserTicket(
                    title = "해리",
                    screeningStartDateTime = LocalDateTime.of(2024, 3, 28, 21, 0),
                    reservationDetail = reservationDetail,
                ),
            )
        }
    }
}

package woowacourse.movie

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.booking.complete.BookingCompleteActivity
import woowacourse.movie.booking.complete.BookingCompleteActivity.Companion.newIntent
import woowacourse.movie.booking.complete.BookingCompleteUiModel

@Suppress("ktlint:standard:function-naming")
class BookingCompleteActivityTest {
    private lateinit var activityScenario: ActivityScenario<BookingCompleteActivity>

    @Before
    fun setup() {
        val uiModel =
            BookingCompleteUiModel(
                title = "해리 포터와 마법사의 돌",
                date = "2025-04-29",
                time = "19:30",
                seats = listOf("A1", "A2"),
                ticketQuantity = 2,
                ticketTotalPrice = 20000,
            )

        val intent =
            newIntent(
                context = getApplicationContext(),
                uiModel = uiModel,
            )

        activityScenario = ActivityScenario.launch(intent)
    }

    @Test
    fun 티켓_정보를_출력한다() {
        onView(withId(R.id.tv_booking_complete_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))

        onView(withId(R.id.tv_booking_complete_movie_date_time))
            .check(matches(withText("2025-04-29 19:30")))

        onView(withId(R.id.tv_booking_complete_ticket_count))
            .check(matches(withText("일반 2명")))

        onView(withId(R.id.booking_complete_seat_textview))
            .check(matches(withText("A1, A2")))

        onView(withId(R.id.tv_booking_complete_ticket_total_price))
            .check(matches(withText("20,000원 (현장 결제)")))
    }
}

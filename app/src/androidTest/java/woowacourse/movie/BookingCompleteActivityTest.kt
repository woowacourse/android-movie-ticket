package woowacourse.movie

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.BookingCompleteActivity.Companion.newIntent

@Suppress("ktlint:standard:function-naming")
class BookingCompleteActivityTest {
    private lateinit var activityScenario: ActivityScenario<BookingCompleteActivity>

    @Before
    fun setup() {
        val intent =
            newIntent(
                context = getApplicationContext(),
                title = "해리 포터와 마법사의 돌",
                date = "2025-04-01",
                time = "09:00",
                ticketCount = 1,
            )

        activityScenario = ActivityScenario.launch(intent)
    }

    @Test
    fun 티켓_장수가_1이면_13000원을_출력한다() {
        onView(withId(R.id.tv_booking_complete_ticket_total_price))
            .check(matches(withText("13,000원 (현장 결제)")))
    }
}

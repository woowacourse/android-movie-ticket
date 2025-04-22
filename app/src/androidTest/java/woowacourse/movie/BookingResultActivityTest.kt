package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.BookingResult.BookingResultActivity
import woowacourse.movie.booking.BookingActivity
import woowacourse.movie.dto.Ticket

class BookingResultActivityTest {
    private lateinit var intent: Intent

    @Before
    fun setupIntent() {
        val ticket = Ticket("해리 포터와 마법사의 돌", "2025.4.17", "10:00", "2", "26000")

        intent =
            Intent(ApplicationProvider.getApplicationContext(), BookingResultActivity::class.java).apply {
                putExtra("TICKET", ticket)
            }
    }

    @Test
    fun `지정된_영화_제목을_표시한다`() {
        ActivityScenario.launch<BookingActivity>(intent).use {
            onView(withText("해리 포터와 마법사의 돌"))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun `지정된_날짜를_표시한다`() {
        ActivityScenario.launch<BookingActivity>(intent).use {
            onView(withText("2025.4.17"))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun `지정된_시간을_표시한다`() {
        ActivityScenario.launch<BookingActivity>(intent).use {
            onView(withText("10:00"))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun `발권된_티켓수에_따른_인원수를_표시한다`() {
        ActivityScenario.launch<BookingActivity>(intent).use {
            onView(withText("일반 2명"))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun `발권된_티켓수에_따른_금액을_표시한다`() {
        ActivityScenario.launch<BookingActivity>(intent).use {
            onView(withText("26000원 (현장 결제)"))
                .check(matches(isDisplayed()))
        }
    }
}

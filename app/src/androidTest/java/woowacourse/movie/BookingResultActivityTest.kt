package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.data.Ticket

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
            onView(withId(R.id.title))
                .check(matches(withText("해리 포터와 마법사의 돌")))
        }
    }

    @Test
    fun `지정된_날짜를_표시한다`() {
        ActivityScenario.launch<BookingActivity>(intent).use {
            onView(withId(R.id.date))
                .check(matches(withText("2025.4.17")))
        }
    }

    @Test
    fun `지정된_시간을_표시한다`() {
        ActivityScenario.launch<BookingActivity>(intent).use {
            onView(withId(R.id.time))
                .check(matches(withText("10:00")))
        }
    }

    @Test
    fun `발권된_티켓수에_따른_인원수를_표시한다`() {
        ActivityScenario.launch<BookingActivity>(intent).use {
            onView(withId(R.id.count))
                .check(matches(withText("일반 2명")))
        }
    }

    @Test
    fun `발권된_티켓수에_따른_금액을_표시한다`() {
        ActivityScenario.launch<BookingActivity>(intent).use {
            onView(withId(R.id.money))
                .check(matches(withText("26000원 (현장 결제)")))
        }
    }
}

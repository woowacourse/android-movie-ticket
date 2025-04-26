package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.activity.seatselection.SeatSelectionActivity
import woowacourse.movie.domain.Ticket
import java.time.LocalDate
import java.time.LocalTime

class SeatSelectionActivityTest {
    private lateinit var scenario: ActivityScenario<SeatSelectionActivity>

    @Before
    fun setup() {
        val ticket = Ticket("해리 포터와 마법사의 돌", LocalDate.of(2025, 4, 1), LocalTime.of(10, 0), 2)
        val intent =
            Intent(ApplicationProvider.getApplicationContext(), SeatSelectionActivity::class.java).apply {
                putExtra("TICKET", ticket)
            }
        scenario = ActivityScenario.launch(intent)
    }

    @Test
    fun `선택한_영화의_제목을_보여준다`() {
        onView(withId(R.id.title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `선택한_좌석들의_총_티켓값을_보여준다`() {
        onView(withId(R.id.a1))
            .perform(click())

        onView(withId(R.id.d2))
            .perform(click())

        onView(withId(R.id.money))
            .check(matches(withText("25000원")))
    }
}

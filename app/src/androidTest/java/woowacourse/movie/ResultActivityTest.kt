package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.SeatGrade
import woowacourse.movie.ui.result.ResultActivity
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class ResultActivityTest {
    private val ticket = Ticket(1L)

    private var intent : Intent

    init {
        ticket.count = 1
        ticket.seats.add(Seat("A",1, SeatGrade.A))
        ticket.date = LocalDate.of(2024, 3, 1)
        ticket.time = "10:00"

        intent = Intent(
            ApplicationProvider.getApplicationContext(),
            ResultActivity::class.java,
        ).putExtra("ticket", ticket)
            .putExtra("screenTitle", "해리 포터와 마법사의 돌")
    }


    @get:Rule
    val activityRule = ActivityScenarioRule<ResultActivity>(intent)

    @Test
    fun show_title_when_activity_starts() {
        onView(withId(R.id.result_title_textview))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun show_screen_date_when_activity_starts() {
        onView(withId(R.id.result_screen_time_textview))
            .check(matches(withText("2024.3.1 10:00")))
    }

    @Test
    fun show_count_when_activity_starts() {
        onView(withId(R.id.result_seats_count_textview))
            .check(matches(withText("1")))
    }

    @Test
    fun show_total_price_when_activity_starts() {
        onView(withId(R.id.result_price_textview))
            .check(matches(withText("12,000")))
    }
}
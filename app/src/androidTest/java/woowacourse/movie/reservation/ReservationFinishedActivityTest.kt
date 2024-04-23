package woowacourse.movie.reservation

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.reservation.finished.ReservationFinishedActivity

class ReservationFinishedActivityTest {
    @Test
    fun `예매한_영화_제목을_보여준다`() {
        val intent =
            Intent(ApplicationProvider.getApplicationContext(), ReservationFinishedActivity::class.java).apply {
                putExtra("movieId", 0)
            }

        ActivityScenario.launch<ReservationFinishedActivity>(intent).onActivity {
            it.intent = intent
        }

        onView(withId(R.id.text_view_reservation_finished_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `예매한_영화_상영일을_보여준다`() {
        val intent =
            Intent(ApplicationProvider.getApplicationContext(), ReservationFinishedActivity::class.java).apply {
                putExtra("movieId", 0)
            }

        ActivityScenario.launch<ReservationFinishedActivity>(intent).onActivity {
            it.intent = intent
        }

        onView(withId(R.id.text_view_reservation_finished_screening_date)).check(matches(withText("2001.11.14")))
    }

    @Test
    fun `예매한_영화_관람인원을_보여준다`() {
        val intent =
            Intent(ApplicationProvider.getApplicationContext(), ReservationFinishedActivity::class.java).apply {
                putExtra("ticketCount", 1)
            }

        ActivityScenario.launch<ReservationFinishedActivity>(intent).onActivity {
            it.intent = intent
        }

        onView(withId(R.id.text_view_reservation_finished_number_of_tickets)).check(matches(withText("1")))
    }

    @Test
    fun `예매한_영화_총_결제금액을_보여준다`() {
        val intent =
            Intent(ApplicationProvider.getApplicationContext(), ReservationFinishedActivity::class.java).apply {
                putExtra("ticketCount", 1)
            }

        ActivityScenario.launch<ReservationFinishedActivity>(intent).onActivity {
            it.intent = intent
        }

        onView(withId(R.id.text_view_reservation_finished_ticket_price)).check(matches(withText("13,000")))
    }
}

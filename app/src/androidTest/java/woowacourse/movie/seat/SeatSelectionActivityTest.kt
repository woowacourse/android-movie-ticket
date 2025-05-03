package woowacourse.movie.seat

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.domain.ticket.MovieTicket
import woowacourse.movie.ui.booking.BookingActivity
import woowacourse.movie.ui.seat.SeatsSelectionActivity
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class SeatSelectionActivityTest {
    @Before
    fun setUp() {
        val ticket =
            MovieTicket(
                1,
                LocalDateTime.of(2025, 4, 18, 12, 0),
                2,
                20000,
                mutableListOf("A1", "A2"),
            )

        val intent =
            Intent(
                ApplicationProvider.getApplicationContext(),
                SeatsSelectionActivity::class.java,
            ).apply {
                putExtra("Ticket", ticket)
            }

        ActivityScenario.launch<BookingActivity>(intent)
    }

    @Test
    fun `영화_제목이_출력된다`() {
        onView(withId(R.id.movie_title))
            .check(matches(withText("승부")))
    }

    @Test
    fun `티켓_총_금액이_출력된다`() {
        onView(withId(R.id.amount))
            .check(matches(withText("20,000원")))
    }

    @Test
    fun `모든_좌석들이_나타난다`() {
        val seats =
            listOf(
                "A1",
                "A2",
                "A3",
                "A4",
                "B1",
                "B2",
                "B3",
                "B4",
                "C1",
                "C2",
                "C3",
                "C4",
                "D1",
                "D2",
                "D3",
                "D4",
                "E1",
                "E2",
                "E3",
                "E4",
            )
        seats.forEach { seat ->
            onView(withText(seat))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun `확인_버튼을_누르면_다이어로그_메시지가_나타난다`() {
        onView(withId(R.id.confirm_button))
            .perform(click())

        onView(withText("정말 예매하시겠습니까?"))
            .check(matches(isDisplayed()))
    }
}

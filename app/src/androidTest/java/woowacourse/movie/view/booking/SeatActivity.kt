package woowacourse.movie.view.booking

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.booking.PeopleCount
import woowacourse.movie.fixture.fakeContext
import woowacourse.movie.view.booking.BookingActivity.Companion.KEY_BOOKING
import woowacourse.movie.view.seat.SeatActivity
import java.time.LocalDate
import java.time.LocalTime

class SeatActivity {
    private lateinit var intent: Intent

    @Before
    fun setUp() {
        intent =
            Intent(
                fakeContext,
                SeatActivity::class.java,
            ).apply {
                val booking =
                    Booking(
                        title = "해리 포터와 마법사의 돌",
                        bookingDate = LocalDate.parse("2025-04-01"),
                        bookingTime = LocalTime.parse("12:00"),
                        count = PeopleCount(2),
                    )
                putExtra(KEY_BOOKING, booking)
            }
        ActivityScenario.launch<SeatActivity>(intent)
    }

    @Test
    fun `전달받은_영화_이름을_출력한다`() {
        onView(withText("해리 포터와 마법사의 돌")).check(matches(isDisplayed()))
    }
}

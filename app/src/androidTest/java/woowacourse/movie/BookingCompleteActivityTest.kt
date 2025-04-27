package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.domain.model.BookedTicket
import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.model.TicketType
import woowacourse.movie.fixture.fakeContext
import woowacourse.movie.ui.complete.BookingCompleteActivity
import java.time.LocalDateTime

class BookingCompleteActivityTest {
    private lateinit var intent: Intent

    @Before
    fun setUp() {
        intent =
            Intent(fakeContext, BookingCompleteActivity::class.java).apply {
                putExtra(
                    "bookedTicket",
                    BookedTicket(
                        "해리 포터와 마법사의 돌",
                        Headcount(2),
                        LocalDateTime.of(2025, 4, 1, 12, 0),
                        Seats().apply {
                            add(Seat(0, 0, TicketType.B_GRADE))
                            add(Seat(2, 3, TicketType.S_GRADE))
                        },
                    ),
                )
            }
        ActivityScenario.launch<BookingCompleteActivity>(intent)
    }

    @Test
    fun `영화_이름을_출력한다`() {
        onView(withId(R.id.tv_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `상영_시간을_출력한다`() {
        onView(withId(R.id.tv_release_date)).check(matches(withText("2025.4.1 12:00")))
    }

    @Test
    fun `예매_인원을_출력한다`() {
        onView(withId(R.id.tv_headcount)).check(matches(withText("일반 2명 | A1, C4")))
    }

    @Test
    fun `예매_가격을_출력한다`() {
        onView(withId(R.id.tv_price)).check(matches(withText("25,000원 (현장 결제)")))
    }
}

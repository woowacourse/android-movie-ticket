package woowacourse.movie.ui.complete

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.domain.model.BookedTicket
import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.model.TicketType
import woowacourse.movie.fixture.fakeContext
import java.time.LocalDateTime

class BookingCompleteActivityTest {
    @Before
    fun setUp() {
        val intent =
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
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `상영_시간을_출력한다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(ViewMatchers.withText("2025.4.1 12:00")))
    }

    @Test
    fun `예매_인원을_출력한다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_headcount))
            .check(ViewAssertions.matches(ViewMatchers.withText("일반 2명 | A1, C4")))
    }

    @Test
    fun `예매_가격을_출력한다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_price))
            .check(ViewAssertions.matches(ViewMatchers.withText("25,000원 (현장 결제)")))
    }
}

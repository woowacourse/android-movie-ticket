package woowacourse.movie.view.complete

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Before
import org.junit.Test
import woowacourse.movie.domain.model.booking.PeopleCount
import woowacourse.movie.domain.model.seat.Column
import woowacourse.movie.domain.model.seat.Row
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.ticket.Ticket
import woowacourse.movie.fixture.fakeContext
import java.time.LocalDate
import java.time.LocalTime

class BookingCompleteActivityTest {
    private lateinit var intent: Intent

    @Before
    fun setUp() {
        intent =
            BookingCompleteActivity.newIntent(
                fakeContext,
                Ticket(
                    "해리 포터와 마법사의 돌",
                    LocalDate.parse("2025-04-01"),
                    LocalTime.parse("12:00"),
                    PeopleCount(2),
                    setOf(Seat(Column(1), Row(1)), Seat(Column(1), Row(2))),
                    26000,
                ),
            )
        ActivityScenario.launch<BookingCompleteActivity>(intent)
    }

    @Test
    fun `전달_받은_영화_이름_상영_시간_예매_인원_예매_가격을_출력한다`() {
        Espresso.onView(ViewMatchers.withText("해리 포터와 마법사의 돌"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("2025.4.1 12:00"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("일반 2명"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("26,000원 (현장 결제)"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}

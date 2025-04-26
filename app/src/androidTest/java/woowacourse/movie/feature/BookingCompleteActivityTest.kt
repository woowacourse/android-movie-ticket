package woowacourse.movie.feature

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.domain.model.BookingInfo
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieDate
import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.domain.model.MovieSeats
import woowacourse.movie.domain.model.MovieTime
import woowacourse.movie.domain.model.TicketCount
import woowacourse.movie.feature.bookingcomplete.view.BookingCompleteActivity
import woowacourse.movie.feature.bookingcomplete.view.BookingCompleteActivity.Companion.newIntent
import woowacourse.movie.feature.mapper.toUi

@Suppress("ktlint:standard:function-naming")
class BookingCompleteActivityTest {
    private lateinit var activityScenario: ActivityScenario<BookingCompleteActivity>

    @Before
    fun setup() {
        val intent =
            newIntent(
                context = getApplicationContext(),
                bookingInfo =
                    BookingInfo(
                        movie =
                            Movie(
                                title = "해리 포터와 마법사의 돌",
                                startDate = MovieDate(2025, 4, 1),
                                endDate = MovieDate(2025, 4, 25),
                                runningTime = 152,
                            ),
                        date = MovieDate(2025, 4, 1),
                        time = MovieTime(9, 0),
                        seats = MovieSeats(setOf(MovieSeat(3, 1))),
                        ticketCount = TicketCount(),
                    ).toUi(),
            )

        activityScenario = ActivityScenario.launch(intent)
    }

    @Test
    fun S_랭크_티켓_장수가_1이면_15000원을_출력한다() {
        onView(withId(R.id.tv_booking_complete_ticket_total_price))
            .check(matches(withText("15,000원 (현장 결제)")))
    }
}

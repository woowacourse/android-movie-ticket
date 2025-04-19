package woowacourse.movie

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.BookingCompleteActivity.Companion.newIntent
import java.time.LocalDate
import java.time.LocalTime

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
                                startDate = LocalDate.of(2025, 4, 1),
                                endDate = LocalDate.of(2025, 4, 25),
                                runningTime = 152,
                                poster = R.drawable.img_poster_harry_potter_and_the_philosophers_stone,
                            ),
                        date = LocalDate.of(2025, 4, 1),
                        movieTime = MovieTime(LocalTime.of(9, 0)),
                        count = 1,
                    ),
            )

        activityScenario = ActivityScenario.launch(intent)
    }

    @Test
    fun 티켓_장수가_1이면_13000원을_출력한다() {
        onView(withId(R.id.tv_booking_complete_ticket_total_price))
            .check(matches(withText("13,000원 (현장 결제)")))
    }
}

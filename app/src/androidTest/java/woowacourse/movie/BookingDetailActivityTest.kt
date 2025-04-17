package woowacourse.movie

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import org.hamcrest.CoreMatchers.anything
import org.junit.Before
import org.junit.Test
import woowacourse.movie.BookingDetailActivity.Companion.newIntent

@Suppress("ktlint:standard:function-naming")
class BookingDetailActivityTest {
    private lateinit var activityScenario: ActivityScenario<BookingDetailActivity>

    @Before
    fun setup() {
        val intent =
            newIntent(
                context = getApplicationContext(),
                title = "해리 포터와 마법사의 돌",
                startDate = "2025-04-01",
                endDate = "2025-04-25",
                runningTime = 152,
                poster = R.drawable.img_poster_harry_potter_and_the_philosophers_stone,
            )

        activityScenario = ActivityScenario.launch(intent)
    }

    @Test
    fun 선택한_날이_평일이면_기본_시간은_9시로_설정된다() {
        onView(withId(R.id.sp_booking_detail_time))
            .check(matches(withSpinnerText("09:00")))
    }

    @Test
    fun 선택한_날이_주말이면_기본_시간은_10시로_설정된다() {
        onView(withId(R.id.sp_booking_detail_date))
            .perform(click())

        onData(anything())
            .atPosition(4)
            .perform(click())

        onView(withId(R.id.sp_booking_detail_time))
            .check(matches(withSpinnerText("10:00")))
    }
}

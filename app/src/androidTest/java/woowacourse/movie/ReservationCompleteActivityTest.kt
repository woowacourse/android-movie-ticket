package woowacourse.movie

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.activity.reservation.ReservationCompleteActivity
import woowacourse.movie.fixture.ActivityFixture

@RunWith(AndroidJUnit4::class)
@Suppress("FunctionName")
class ReservationCompleteActivityTest {
    val intent =
        ReservationCompleteActivity.newIntent(
            ApplicationProvider.getApplicationContext<Context>(),
            ActivityFixture.reservationSeatDto,
        )

    @get:Rule
    val activityRule = ActivityScenarioRule<ReservationCompleteActivity>(intent)

    @Test
    fun 영화_예매_완료_화면이_표시된다() {
        onView(withId(R.id.booking_success))
            .check(matches(isDisplayed()))

        onView(withText("해리포터와 마법사의 돌0"))
            .check(matches(isDisplayed()))
        onView(withText("2025.04.03 11:00"))
            .check(matches(isDisplayed()))
        onView(withText("일반 2명"))
            .check(matches(isDisplayed()))
        onView(withText("24,000원 (현장 결제) | A1, A2"))
            .check(matches(isDisplayed()))
    }
}

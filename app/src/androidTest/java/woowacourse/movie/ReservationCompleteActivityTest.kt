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
import woowacourse.movie.activity.ReservationCompleteActivity
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.MemberCount
import woowacourse.movie.dto.ReservationDto
import woowacourse.movie.global.ServiceLocator
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
@Suppress("FunctionName")
class ReservationCompleteActivityTest {
    val bookingStatus =
        BookingStatus(
            ServiceLocator.movies[0],
            true,
            MemberCount(2),
            LocalDateTime.of(2025, 4, 3, 11, 0, 0),
        )
    val intent =
        ReservationCompleteActivity.newIntent(
            ApplicationProvider.getApplicationContext<Context>(),
            ReservationDto.fromBookingStatus(bookingStatus),
        )

    @get:Rule
    val activityRule = ActivityScenarioRule<ReservationCompleteActivity>(intent)

    @Test
    fun 영화_예매_완료_화면이_표시된다() {
        onView(withId(R.id.booking_success))
            .check(matches(isDisplayed()))

        onView(withText("해리포터와 마법사의 돌"))
            .check(matches(isDisplayed()))
        onView(withText("2025.04.03 11:00"))
            .check(matches(isDisplayed()))
        onView(withText("일반 2명"))
            .check(matches(isDisplayed()))
        onView(withText("26,000원 (현장 결제)"))
            .check(matches(isDisplayed()))
    }
}

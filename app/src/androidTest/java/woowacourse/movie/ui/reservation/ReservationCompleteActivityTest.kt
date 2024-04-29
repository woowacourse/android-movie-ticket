package woowacourse.movie.ui.reservation

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R

@RunWith(AndroidJUnit4::class)
class ReservationCompleteActivityTest {
    @get:Rule
    val activityRule: ActivityScenarioRule<ReservationCompleteActivity> =
        ActivityScenarioRule<ReservationCompleteActivity>(
            Intent(ApplicationProvider.getApplicationContext(), ReservationCompleteActivity::class.java).apply {
                putExtra("reservationId", testFixtureReservationId())
            },
        )

    @Test
    fun 예약한_영화의_제목을_표시한다() {
        onView(withId(R.id.tv_reservation_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun 예약한_영화의_러닝타임을_표시한다() {
        onView(withId(R.id.tv_reservation_date)).check(matches(withText("2024-03-01")))
    }

    @Test
    fun 상영_영화_예매_수를_표시한다() {
        onView(withId(R.id.tv_reservation_count)).check(matches(withText("일반 1명")))
    }

    @Test
    fun 상영_영화_예매_가격을_표시한다() {
        onView(withId(R.id.tv_reservation_amount)).check(matches(withText("13,000원 (현장 결제)")))
    }

    private fun testFixtureReservationId(): Result<Int> = throw NotImplementedError() // TODO: save new Reservation
}

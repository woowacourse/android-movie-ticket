package woowacourse.movie.reservation

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.TestFixture.DUMMY_MOVIE

@RunWith(AndroidJUnit4::class)
class ReservationActivityTest {
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            ReservationActivity::class.java,
        ).also { it.putExtra("movie", DUMMY_MOVIE) }

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule<ReservationActivity>(intent)

    @Test
    fun `전달_받은_영화_제목이_표시된다`() {
        onView(withId(R.id.movie_title))
            .check(matches(withText("제목")))
    }

    @Test
    fun `전달_받은_영화_설명이_표시된다`() {
        onView(withId(R.id.content))
            .check(matches(withText("설명")))
    }

    @Test
    fun `수량의_기본_값은_0이다`() {
        onView(withId(R.id.quantity))
            .check(matches(withText("0")))
    }

    @Test
    fun `플러스_버튼_클릭시_수량이_증가한다`() {
        onView(withId(R.id.btn_plus))
            .perform(click())

        onView(withId(R.id.quantity))
            .check(matches(withText("1")))
    }

    @Test
    fun `마이너스_버튼_클릭시_수량이_감소한다`() {
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withId(R.id.btn_minus)).perform(click())

        onView(withId(R.id.quantity))
            .check(matches(withText("0")))
    }

    @Test
    fun `예약_완료_버튼_클릭시_예약완료화면으로_이동한다`() {
        onView(withId(R.id.btn_reservation_completed))
            .perform(click())

        onView(withId(R.id.message))
            .check(matches(isDisplayed()))
    }
}

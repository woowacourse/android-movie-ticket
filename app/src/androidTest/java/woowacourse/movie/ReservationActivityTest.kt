package woowacourse.movie

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
import woowacourse.movie.model.Movie
import woowacourse.movie.reservation.ReservationActivity

@RunWith(AndroidJUnit4::class)
class ReservationActivityTest {
    private val movie =
        Movie(R.drawable.poster, "영화 제목", "영화 설명", "2024.3.1", 152)
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            ReservationActivity::class.java,
        ).also { it.putExtra("movie", movie) }

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule<ReservationActivity>(intent)

    @Test
    fun `활동_시작시_영화_상세정보가_표시된다`() {
        onView(withId(R.id.movie_title))
            .check(matches(withText("영화 제목")))
    }

    @Test
    fun `플러스_버튼_클릭시_수량이_증가한다`() {
        onView(withId(R.id.btn_plus))
            .perform(click())

        onView(withId(R.id.quantity))
            .check(matches(withText("2")))
    }

    @Test
    fun `마이너스_버튼_클릭시_수량이_감소한다`() {
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withId(R.id.btn_minus)).perform(click())

        onView(withId(R.id.quantity))
            .check(matches(withText("1")))
    }

    @Test
    fun `예약_완료_버튼_클릭시_예약완료화면으로_이동한다`() {
        onView(withId(R.id.btn_reservation_completed))
            .perform(click())

        onView(withId(R.id.message))
            .check(matches(isDisplayed()))
    }
}

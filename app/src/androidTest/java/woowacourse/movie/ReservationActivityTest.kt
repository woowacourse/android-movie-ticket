package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.model.UiMovie
import woowacourse.movie.reservation.ReservationActivity

@RunWith(AndroidJUnit4::class)
class ReservationActivityTest {
    private val uiMovie =
        UiMovie(R.drawable.poster, "해리 포터와 마법사의 돌", "description", "2024.3.1", 152, 13000)
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            ReservationActivity::class.java,
        ).also { it.putExtra("movie", uiMovie) }

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule<ReservationActivity>(intent)

    @Test
    fun `활동_시작시_영화_상세정보가_표시된다`() {
        onView(withId(R.id.movie_title))
            .check(matches(withText(uiMovie.title)))
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
}

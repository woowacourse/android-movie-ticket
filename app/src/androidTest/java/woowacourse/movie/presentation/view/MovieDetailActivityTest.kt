package woowacourse.movie.presentation.view

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
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

@RunWith(AndroidJUnit4::class)
class MovieDetailActivityTest {
    private val testContext = ApplicationProvider.getApplicationContext<Context>()
    private val intent: Intent = detailActivityIntent(testContext)

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieDetailActivity>(intent)

    @Test
    fun `영화_목록_화면에서_전달_받은_영화_정보를_화면에_나타낸다`() {
        onView(withId(R.id.posterImage)).check(matches(isDisplayed()))
        onView(withId(R.id.title)).check(matches(withText(TITLE)))
        onView(withId(R.id.screeningDate)).check(
            matches(
                withText(
                    testContext.getString(
                        R.string.screening_date_format,
                        SCREENING_DATE,
                    ),
                ),
            ),
        )
        onView(withId(R.id.runningTime)).check(
            matches(
                withText(
                    testContext.getString(
                        R.string.running_time_format,
                        RUNNING_TIME,
                    ),
                ),
            ),
        )
        onView(withId(R.id.summary)).check(matches(withText(SUMMARY)))
    }

    @Test
    fun `영화_상세_화면에서_초기_예매_수량은_1이다`() {
        onView(withId(R.id.reservationInfo))
            .check(matches(withText("1")))
    }

    @Test
    fun `영화_상세_화면에서_예매_수량을_증가시키면_예매_수량이_증가한다`() {
        // when
        onView(withId(R.id.plusButton)).perform(click())
        // then
        onView(withId(R.id.reservationInfo)).check(matches(withText("2")))
    }

    @Test
    fun `영화_상세_화면에서_예매_수량을_감소시키면_예매_수량이_감소한다`() {
        // given
        onView(withId(R.id.plusButton)).perform(click())
        // when
        onView(withId(R.id.minusButton)).perform(click())
        // then
        onView(withId(R.id.reservationInfo)).check(matches(withText("1")))
    }

    @Test
    fun `화면을_회전시켜도_예매_수량이_초기화되지_않는다`() {
        // given
        onView(withId(R.id.plusButton)).perform(click())
        // when
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        // then
        onView(withId(R.id.reservationInfo)).check(matches(withText("2")))
    }
}

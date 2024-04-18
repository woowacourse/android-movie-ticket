package woowacourse.movie.presentation.view

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `영화_목록이_화면에_표시된다`() {
        // given
        onView(withId(R.id.movieList)).check(matches(isDisplayed()))
    }

    @Test
    fun `영화_목록에서_영화를_클릭하면_영화_상세_화면으로_이동한다`() {
        // when
        onData(anything())
            .inAdapterView(withId(R.id.movieList))
            .atPosition(0)
            .onChildView(withId(R.id.reserveButton))
            .perform(click())

        // then
        onView(withId(R.id.movie_detail_layout)).check(matches(isDisplayed()))
    }
}

package woowacourse.movie.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.presentation.screen.movie.ScreeningMovieActivity

@RunWith(AndroidJUnit4::class)
class ScreeningMovieActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(ScreeningMovieActivity::class.java)

    @Test
    fun 영화_리스트를_보여준다() {
        onView(withId(R.id.movie_list))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 예약버튼이_눌리면_mainlayout은_사라져야한다() {
        onView(withId(R.id.reservation_button))
            .perform(click())

        onView(withId(R.id.screening_layout))
            .check(doesNotExist())
    }
}

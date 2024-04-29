package woowacourse.movie.presentation.ui.screen

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R

@RunWith(AndroidJUnit4::class)
class ScreenActivityTest {
    @get:Rule
    val activityRule: ActivityScenarioRule<ScreenActivity> =
        ActivityScenarioRule(ScreenActivity::class.java)

    @Test
    fun `상영_목록이_표시된다`() {
        onView(withId(R.id.rv_screen)).check(matches(isDisplayed()))
    }
}

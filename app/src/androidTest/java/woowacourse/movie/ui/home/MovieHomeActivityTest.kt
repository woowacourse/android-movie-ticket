package woowacourse.movie.ui.home

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
class MovieHomeActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieHomeActivity::class.java)

    @Test
    fun `화면이_띄워지면_영화_목록이_보인다`() {
        onView(withId(R.id.movie_content_list)).check(matches(isDisplayed()))
    }
}

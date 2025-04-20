package woowacourse.movie

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

@RunWith(AndroidJUnit4::class)
class MovieTicketActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieTicketActivity::class.java)

    @Test
    fun 영화_목록이_표시된다() {
        onView(withId(R.id.movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.movie_item_root))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 초기_화면에_리스트_아이템의_요소들이_로드된다() {
        onView(withId(R.id.movie_title))
            .check(
                matches(
                    withText(
                        MovieFixture.HARRY_POTTER_TITLE
                    )
                )
            )
        onView(withId(R.id.movie_date))
            .check(
                matches(
                    withText(
                        MovieFixture.HARRY_POTTER_DATE
                    )
                )
            )

        onView(withId(R.id.movie_running_time))
            .check(
                matches(
                    withText(
                        MovieFixture.HARRY_POTTER_RUNNING_TIME
                    )
                )
            )
    }
}

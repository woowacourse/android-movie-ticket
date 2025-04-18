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
@Suppress("FunctionName")
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

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
                        "해리포터와 마법사의 돌",
                    ),
                ),
            )
        onView(withId(R.id.movie_date))
            .check(
                matches(
                    withText(
                        "상영일: 2025.04.01",
                    ),
                ),
            )

        onView(withId(R.id.movie_running))
            .check(
                matches(
                    withText(
                        "러닝타임: 125분",
                    ),
                ),
            )
    }
}

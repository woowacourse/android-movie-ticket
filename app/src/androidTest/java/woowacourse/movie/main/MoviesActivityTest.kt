package woowacourse.movie.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.ui.view.movies.MoviesActivity

@RunWith(AndroidJUnit4::class)
class MoviesActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MoviesActivity::class.java)

    @DisplayName("영화 제목이 표시된다")
    @Test
    fun movieTitleTest() {
        val expected = listOf("승부", "미키 17", "야당", "범죄도시")

        displayCheck(expected)
    }

    @DisplayName("상영일이 표시된다")
    @Test
    fun screeningDateTest() {
        val expected =
            listOf(
                "상영일: 2025-03-26 ~ 2025-04-26",
                "상영일: 2025-04-01 ~ 2025-04-29",
                "상영일: 2025-05-01 ~ 2025-05-29",
                "상영일: 2025-05-02 ~ 2025-05-29",
            )

        displayCheck(expected)
    }

    @DisplayName("포스터가 표시된다")
    @Test
    fun posterTest() {
        val expected =
            listOf(Visibility.VISIBLE, Visibility.VISIBLE, Visibility.VISIBLE, Visibility.VISIBLE)

        expected.forEach { visibility ->
            onView(withId(R.id.movies))
                .check(matches(withEffectiveVisibility(visibility)))
        }
    }

    @DisplayName("러닝타임이 표시된다")
    @Test
    fun runningTimeTest() {
        val expected =
            listOf(
                "러닝타임: 115분",
                "러닝타임: 137분",
                "러닝타임: 123분",
                "러닝타임: 135분",
            )

        displayCheck(expected)
    }

    private fun displayCheck(expected: List<String>) {
        expected.forEachIndexed { index, title ->
            onView(withId(R.id.movies))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(index))
            onView(withText(title))
                .check(matches(isDisplayed()))
        }
    }
}

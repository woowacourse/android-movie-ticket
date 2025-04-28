package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.movies.MoviesActivity

@Suppress("ktlint:standard:function-naming")
class MoviesActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MoviesActivity::class.java)

    @Test
    fun 리사이클러뷰로_영화가_출력된다() {
        onView(withId(R.id.movies_view))
            .check(matches(isDisplayed()))
    }
}

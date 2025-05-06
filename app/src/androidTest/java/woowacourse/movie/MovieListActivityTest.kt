package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.movieList.MovieListActivity

class MovieListActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @Test
    fun `화면에_해리포터_영화의_제목이_표시된다`() {
        onView(withText("해리 포터와 마법사의 돌"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `화면에_해리포터_영화의_상영날짜가_표시된다`() {
        onView(withText("상영일 : 2025.4.1 ~ 2025.4.25"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `화면에_해리포터_영화의_러닝타임이_표시된다`() {
        onView(withText("러닝타임 : 152분"))
            .check(matches(isDisplayed()))
    }
}

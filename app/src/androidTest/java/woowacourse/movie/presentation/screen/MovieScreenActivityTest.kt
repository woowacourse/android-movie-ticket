package woowacourse.movie.presentation.screen

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.data.MockMovies
import woowacourse.movie.presentation.screen.adapter.MovieScreenAdapter

@RunWith(AndroidJUnit4::class)
class MovieScreenActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieScreenActivity::class.java)

    @Test
    fun `영화_목록을_볼_수_있다`() {
        onView(withId(R.id.movie_recycler_view)).check(matches(isDisplayed()))
    }
}

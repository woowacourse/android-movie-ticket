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
    private lateinit var context: Context
    private lateinit var adapter: MovieScreenAdapter

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        adapter =
            MovieScreenAdapter(
                context, "ad_placeholder",
                listOf(
                    MockMovies.defaultMovie,
                    MockMovies.defaultMovie,
                    MockMovies.defaultMovie,
                ),
            ) {}
    }

    @Test
    fun `영화_목록을_볼_수_있다`() {
        onView(withId(R.id.movie_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun `첫_번째_아이템이_올바른_movie를_표시한다`() {
        val viewType = adapter.getItemViewType(0)
        assertEquals(0, viewType)
    }

    @Test
    fun `네_번째_아이템이_광고를_표시한다`() {
        val viewType = adapter.getItemViewType(3)
        assertEquals(1, viewType)
    }
}

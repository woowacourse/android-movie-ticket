import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.ui.movielist.MovieListActivity

class MovieListTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @Test
    fun `영화_포스터가_있는_리스트가_표시된다`() {
        onData(allOf())
            .inAdapterView(withId(R.id.recycler_movies))
            .atPosition(0)
            .check(matches(isDisplayed()))
    }
}

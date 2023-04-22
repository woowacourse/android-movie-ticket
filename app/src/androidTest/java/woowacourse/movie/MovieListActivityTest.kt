package woowacourse.movie

import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import woowacourse.movie.activity.movielist.MovieListActivity

class MovieListActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    // @Test
    // fun 버튼을_클릭하면_상세_액티비티를_띄운다() {
    //     onView(withId(R.id.list_view)).check(matches(isDisplayed()))
    //
    // }
}

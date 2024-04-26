import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.CoreMatchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.activity.MovieListActivity
import woowacourse.movie.model.movie.MovieDetail
import woowacourse.movie.model.movie.RunningTime
import woowacourse.movie.model.movie.Synopsis
import woowacourse.movie.model.movie.Title
import woowacourse.movie.uimodel.MovieBrief

@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieListActivityTest {
    private val movieBrief =
        MovieBrief(
            "차람과 하디의 진지한 여행기",
            "상영일: 2024.2.25",
            "230분" ,
        )

    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @Test
    fun listViewDisplaysMovieInfo() {
        onView(withId(R.id.movies_list_item)).check(matches(isDisplayed()))

        onData(anything())
            .inAdapterView(withId(R.id.movies_list_item))
            .atPosition(0)
            .onChildView(withId(R.id.movie_title))
            .check(matches(withText(movieBrief.title)))
    }

    @Test
    fun clickButtonInListViewItem_opensNewActivity() {
        onData(anything())
            .inAdapterView(withId(R.id.movies_list_item))
            .atPosition(0)
            .onChildView(withId(R.id.movie_details_button))
            .perform(click())

        onView(withId(R.id.scroll_view))
            .check(matches(isDisplayed()))
    }
}

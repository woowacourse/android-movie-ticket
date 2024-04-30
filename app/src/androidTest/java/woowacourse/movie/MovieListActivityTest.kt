import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
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
import woowacourse.movie.model.movieInfo.MovieDate
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.movieList.MovieListActivity
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieListActivityTest {
    private val movie =
        MovieInfo(
            Title("차람과 하디의 진지한 여행기"),
            MovieDate(LocalDate.of(2024, 2, 25)),
            RunningTime(230),
            Synopsis("wow!"),
        )

    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @Test
    fun whenListViewItemIsVisible_thenDisplayMovieInfo() {
        onView(withId(R.id.movies_list_item)).check(matches(isDisplayed()))

        onData(anything())
            .inAdapterView(withId(R.id.movies_list_item))
            .atPosition(0)
            .onChildView(withId(R.id.movie_title))
            .check(matches(withText(movie.title.toString())))
    }

    @Test
    fun whenDetailsButtonClicked_inListViewItem_thenOpenNewActivity() {
        onData(anything())
            .inAdapterView(withId(R.id.movies_list_item))
            .atPosition(0)
            .onChildView(withId(R.id.movie_details_button))
            .perform(click())

        onView(withId(R.id.scroll_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 다음화면에서_뒤로가기버튼클릭_영화목록화면표시() {
        onData(anything())
            .inAdapterView(withId(R.id.movies_list_item))
            .atPosition(0)
            .onChildView(withId(R.id.movie_details_button))
            .perform(click())
        pressBack()
        onView(withId(R.id.movies_list_item)).check(matches(isDisplayed()))
    }
}

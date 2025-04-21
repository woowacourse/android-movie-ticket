package woowacourse.movie.view.movies

import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.anything
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.view.fixture.MovieFixture

class MoviesActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MoviesActivity::class.java)

    private lateinit var movieItem: DataInteraction

    @Before
    fun setUp() {
        movieItem =
            onData(anything())
                .inAdapterView(withId(R.id.lv_movie))
                .atPosition(0)
    }

    @Test
    fun `영화의_제목이_보여진다`() {
        movieItem
            .onChildView(withId(R.id.tv_title))
            .check(matches(withText(MovieFixture.harryPotter.title)))
    }

    @Test
    fun `영화의_상영일이_보여진다`() {
        movieItem
            .onChildView(withId(R.id.tv_date))
            .check(matches(withText("상영일: 2025.4.1 ~ 2025.4.25")))
    }

    @Test
    fun `영화의_러닝타임이_보여진다`() {
        movieItem
            .onChildView(withId(R.id.tv_running_time))
            .check(matches(withText(MovieFixture.harryPotter.runningTime)))
    }

    @Test
    fun `영화의_포스터가_보여진다`() {
        movieItem
            .onChildView(withId(R.id.iv_poster))
            .check(matches(isDisplayed()))
    }
}

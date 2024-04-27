package woowacourse.movie.presentation.screening

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.R
import woowacourse.movie.data.FakeMovieRepository
import woowacourse.movie.data.MovieRepositoryFactory

class ScreeningMovieActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(ScreeningMovieActivity::class.java)

    @Before
    fun setUp() {
        MovieRepositoryFactory.setMovieRepository(repository = FakeMovieRepository())
    }

    @After
    fun tearDown() {
        MovieRepositoryFactory.clear()
    }

    @Test
    @DisplayName("ScreeningMovieActivity 가 화면에 보여지는지 테스트")
    fun test_isActivityInView() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("상영중인 영화가 화면에 보여지는지 테스트")
    fun listviewTest() {
        // given
        val screeningMovieUiModel = screenMovieUiModel()
        val title = screeningMovieUiModel.title
        val screenDate = screeningMovieUiModel.screenDate
        val runningTime = screeningMovieUiModel.runningTime
        // when
        val dataInteraction =
            onData(`is`(withItemContent(containsString(title))))
                .inAdapterView(withId(R.id.rv_screening_movie))
                .atPosition(0)
        // then
        dataInteraction.onChildView(withId(R.id.tv_movie_running_time))
            .check(matches(withText(runningTime)))
        dataInteraction.onChildView(withId(R.id.tv_movie_running_date))
            .check(matches(withText(screenDate)))
        dataInteraction.onChildView(withId(R.id.tv_movie_title))
            .check(matches(withText(title)))
    }

    private fun withItemContent(itemTextMatcher: Matcher<String>): Matcher<ScreeningMovieUiModel> {
        return object :
            TypeSafeMatcher<ScreeningMovieUiModel>(ScreeningMovieUiModel::class.java) {
            override fun matchesSafely(screeningMovieUiModel: ScreeningMovieUiModel): Boolean {
                return itemTextMatcher.matches(screeningMovieUiModel.title)
            }

            override fun describeTo(description: Description) {
                description.appendText("with item content matching: ")
                itemTextMatcher.describeTo(description)
            }
        }
    }
}

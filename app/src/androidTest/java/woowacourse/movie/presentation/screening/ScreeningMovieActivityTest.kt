package woowacourse.movie.presentation.screening

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
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
    fun recyclerViewTest() {
        // given
        val screeningMovieUiModel = screenMovieUiModel()
        val title = screeningMovieUiModel.title
        val screenDate = screeningMovieUiModel.screenDate
        val runningTime = screeningMovieUiModel.runningTime
        // when : title 과 일치하는 아이템이 화면에 보여질 때까지 스크롤
        val viewInteraction = onView(withId(R.id.rv_screening_movie))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(
                        withText(
                            title
                        )
                    )
                ).atPosition(0)
            )

        // then : 해당 View 가 screenDate 와 runningTime 을 가지고 있는지 확인
        viewInteraction.check(matches(hasDescendant(withText(screenDate))))
        viewInteraction.check(
            matches(hasDescendant(withText(runningTime)))
        )
    }
}

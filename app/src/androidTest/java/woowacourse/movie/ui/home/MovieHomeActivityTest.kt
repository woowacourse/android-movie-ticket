package woowacourse.movie.ui.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.ui.home.adapter.MovieViewHolder

@RunWith(AndroidJUnit4::class)
class MovieHomeActivityTest {
    private val movieContent: MovieContent = MovieContentsImpl.find(0L)

    @get:Rule
    val activityRule = ActivityScenarioRule(MovieHomeActivity::class.java)

    @Test
    fun `화면이_띄워지면_영화_목록이_보인다`() {
        onView(withId(R.id.movie_content_list)).check(matches(isDisplayed()))
    }

    @Test
    fun `화면이_띄워지면_영화_제목이_보인다`() {
        onView(withId(R.id.movie_content_list))
            .perform(RecyclerViewActions.scrollToPosition<MovieViewHolder>(0))
            .check(matches(hasDescendant(allOf(withText(movieContent.title), isDisplayed()))))
    }

    @Test
    fun `화면이_띄워지면_상영일이_보인다`() {
        onView(withId(R.id.movie_content_list))
            .perform(RecyclerViewActions.scrollToPosition<MovieViewHolder>(0))
            .check(
                matches(
                    hasDescendant(allOf(withText("상영일: 2024.03.01 ~ 2024.03.28"), isDisplayed())),
                ),
            )
    }

    @Test
    fun `화면이_띄워지면_러닝타임이_보인다`() {
        onView(withId(R.id.movie_content_list))
            .perform(RecyclerViewActions.scrollToPosition<MovieViewHolder>(0))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withText("러닝타임: ${movieContent.runningTime}분"),
                            isDisplayed(),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun `화면이_띄워지면_지금_예매_버튼이_보인다`() {
        onView(withId(R.id.movie_content_list))
            .perform(RecyclerViewActions.scrollToPosition<MovieViewHolder>(0))
            .check(
                matches(
                    hasDescendant(allOf(withText("지금 예매"), isDisplayed())),
                ),
            )
    }
}

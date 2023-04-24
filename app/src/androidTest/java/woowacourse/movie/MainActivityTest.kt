package woowacourse.movie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.activity.MainActivity
import woowacourse.movie.movie.MovieMockData

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun 리사이클러뷰_첫번째_아이템에_영화데이터의_첫번째_데이터가_들어있다() {
        onView(withId(R.id.rv_movie_list))
            .performScrollTo(0)
            .checkMovieTitleIsDisplayed(0)
    }

    @Test
    fun 리사이클러뷰_두번째_아이템에_영화데이터의_두번째_데이터가_들어있다() {
        onView(withId(R.id.rv_movie_list))
            .performScrollTo(1)
            .checkMovieTitleIsDisplayed(1)
    }

    @Test
    fun 리사이클러뷰_세번째_아이템에_영화데이터의_세번째_데이터가_들어있다() {
        onView(withId(R.id.rv_movie_list))
            .performScrollTo(2)
            .checkMovieTitleIsDisplayed(2)
    }

    @Test
    fun 리사이클러뷰_네번째_아이템에_광고가_표시된다() {
        onView(withId(R.id.rv_movie_list))
            .performScrollTo(3)
            .checkImageIsDisplayed(R.id.iv_ad)
    }

    @Test
    fun 리사이클러뷰_다섯번째_아이템에_영화데이터의_다섯번째_데이터가_들어있다() {
        onView(withId(R.id.rv_movie_list))
            .performScrollTo(4)
            .checkMovieTitleIsDisplayed(4)
    }

    private fun ViewInteraction.performScrollTo(position: Int) =
        perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))

    private fun ViewInteraction.checkMovieTitleIsDisplayed(position: Int) =
        check(
            matches(
                hasDescendant(
                    allOf(
                        withText(MovieMockData.movies10000[position].title),
                        isDisplayed(),
                    ),
                ),
            ),
        )

    private fun ViewInteraction.checkImageIsDisplayed(id: Int) =
        check(
            matches(
                hasDescendant(
                    allOf(
                        withId(id),
                        isDisplayed(),
                    ),
                ),
            ),
        )
}

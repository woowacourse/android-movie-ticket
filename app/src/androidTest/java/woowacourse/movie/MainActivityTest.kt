package woowacourse.movie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
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
    fun 메인액티비티는_영화리사이클러뷰를_가지고_있다() {
        onView(withId(R.id.rv_movie_list)).check(matches(isDisplayed()))
    }

    @Test
    fun 리사이클러뷰_첫번째_아이템에_영화데이터의_첫번째_데이터가_들어있다() {
        onView(withId(R.id.rv_movie_list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withText(MovieMockData.movies10000[0].title),
                            isDisplayed(),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun 리사이클러뷰_두번째_아이템에_영화데이터의_두번째_데이터가_들어있다() {
        onView(withId(R.id.rv_movie_list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withText(MovieMockData.movies10000[1].title),
                            isDisplayed(),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun 리사이클러뷰_세번째_아이템에_영화데이터의_세번째_데이터가_들어있다() {
        onView(withId(R.id.rv_movie_list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withText(MovieMockData.movies10000[2].title),
                            isDisplayed(),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun 리사이클러뷰_네번째_아이템에_광고가_표시된다() {
        onView(withId(R.id.rv_movie_list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withId(R.id.iv_ad),
                            isDisplayed(),
                        ),
                    ),
                ),
            )
    }
}

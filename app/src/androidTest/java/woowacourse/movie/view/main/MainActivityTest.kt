package woowacourse.movie.view.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.feature.main.MainActivity

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `첫번째_아이템에_영화_제목이_표시된다`() {
        onView(withId(R.id.rv_main_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withId(R.id.tv_item_movie_title),
                            isDisplayed(),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun `첫번째_아이템에_상영일이_표시된다`() {
        onView(withId(R.id.rv_main_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withId(R.id.tv_item_movie_screening_date),
                            isDisplayed(),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun `첫번째_아이템에_포스터가_표시된다`() {
        onView(withId(R.id.rv_main_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withId(R.id.iv_item_movie_poster),
                            isDisplayed(),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun `첫번째_아이템에_러닝타임이_표시된다`() {
        onView(withId(R.id.rv_main_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withId(R.id.tv_item_movie_running_time),
                            isDisplayed(),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun `네번째_아이템에_광고배너가_표시된다`() {
        onView(withId(R.id.rv_main_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withId(R.id.iv_ad_banner),
                            isDisplayed(),
                        ),
                    ),
                ),
            )
    }
}

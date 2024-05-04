package woowacourse.movie.presentation.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.presentation.adapter.AdViewHolder
import woowacourse.movie.presentation.adapter.MovieViewHolder

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `영화_목록이_화면에_표시된다`() {
        // given
        onView(withId(R.id.movieList)).check(matches(isDisplayed()))
    }

    @Test
    fun `영화_목록의_아이템_중_영화가_표시된다`() {
        repeat(4) { idx ->
            onView(withId(R.id.movieList))
                .perform(
                    RecyclerViewActions.scrollToHolder(
                        instanceOf(MovieViewHolder::class.java)
                    ).atPosition(idx)
                )
        }
    }

    @Test
    fun `영화_목록의_아이템_중_광고가_표시된다`() {
        repeat(4) { idx ->
            onView(withId(R.id.movieList))
                .perform(
                    RecyclerViewActions.scrollToHolder(
                        instanceOf(AdViewHolder::class.java)
                    ).atPosition(idx)
                )
        }
    }
}

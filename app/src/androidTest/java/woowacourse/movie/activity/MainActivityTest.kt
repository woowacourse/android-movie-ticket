package woowacourse.movie.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun 영화가_3번_출력되면_광고가_1번_출력된다() {
        // given
        onView(withId(R.id.main_movie_list)).check(
            matches(
                atPosition(
                    0,
                    withId(R.id.root_movie_item)
                )
            )
        )
        onView(withId(R.id.main_movie_list)).check(
            matches(
                atPosition(
                    1,
                    withId(R.id.root_movie_item)
                )
            )
        )
        onView(withId(R.id.main_movie_list)).check(
            matches(
                atPosition(
                    2,
                    withId(R.id.root_movie_item)
                )
            )
        )

        // when

        // then
        onView(withId(R.id.main_movie_list)).check(
            matches(
                atPosition(
                    3,
                    withId(R.id.root_advertisement_item)
                )
            )
        )
    }

    @Test
    fun 영화_목록은_10000개가_넘게_표시된다() {
        // given

        // when

        // then
        onView(withId(R.id.main_movie_list)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10000
            )
        )
    }
}

package woowacourse.movie.main.view

import AdvertisementViewHolder
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.main.view.adapter.MovieViewHolder

@RunWith(AndroidJUnit4::class)
class MovieMainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieMainActivity::class.java)

    @Test
    fun `영화_목록이_화면에_표시된다`() {
        onView(withId(R.id.movieRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun `영화_목록의_3번째_아이템은_영화가_보여진다`() {
        onView(withId(R.id.movieRecyclerView)).check(
            matches(
                matchViewHolderAtPosition(2, MovieViewHolder::class.java),
            ),
        )
    }

    @Test
    fun `영화_목록의_4번째_아이템은_광고가_보여진다`() {
        onView(withId(R.id.movieRecyclerView)).check(
            matches(
                matchViewHolderAtPosition(3, AdvertisementViewHolder::class.java),
            ),
        )
    }

    private fun matchViewHolderAtPosition(
        position: Int,
        viewHolderClass: Class<out RecyclerView.ViewHolder>,
    ): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {}

            override fun matchesSafely(view: View?): Boolean {
                if (view !is RecyclerView) return false
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                return viewHolder != null &&
                    viewHolderClass.isInstance(
                        viewHolder,
                    )
            }
        }
    }
}

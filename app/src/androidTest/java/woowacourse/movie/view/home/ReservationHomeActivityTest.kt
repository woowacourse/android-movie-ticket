package woowacourse.movie.view.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.TestFixture
import woowacourse.movie.TestFixture.movies
import woowacourse.movie.view.home.adapter.viewholder.AdvertisementViewHolder
import woowacourse.movie.view.home.adapter.viewholder.MovieViewHolder

@RunWith(AndroidJUnit4::class)
class ReservationHomeActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(ReservationHomeActivity::class.java)

    @Test
    fun `영화_목록을_보여준다`() {
        onView(withId(R.id.recycler_view_reservation_home)).check(matches(isDisplayed()))
    }

    @Test
    fun `영화_목록을_3번째_아이템으로_스크롤_했을_시_영화가_보여진다`() {
        onView(withId(R.id.recycler_view_reservation_home))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(THIRD_ITEM_POSITION),
            ).check(
                matches(
                    matchViewHolderAtPosition(THIRD_ITEM_POSITION, MovieViewHolder::class.java),
                ),
            )
    }

    @Test
    fun `영화_목록을_4번째_아이템으로_스크롤_했을_시_광고가_보여진다`() {
        onView(withId(R.id.recycler_view_reservation_home))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(FOURTH_ITEM_POSITION),
            ).check(
                matches(
                    matchViewHolderAtPosition(
                        FOURTH_ITEM_POSITION,
                        AdvertisementViewHolder::class.java,
                    ),
                ),
            )
    }

    @Test
    fun `영확_목록을_스크롤했을_때_첫_번째_위치의_MovieViewHolder는_영화_제목을_보여준다`() {
        onView(withId(R.id.recycler_view_reservation_home))
            .perform(
                RecyclerViewActions.scrollToHolder(
                    instanceOf(MovieViewHolder::class.java),
                ).atPosition(FIRST_ITEM_POSITION),
            )

        onView(withText(movies[TestFixture.FIRST_MOVIE_ITEM_POSITION].title)).check(matches(isDisplayed()))
    }

    @Test
    fun `영화_목록을_스크롤했을_때_첫_번째_위치의_AdvertisementViewHolder는_광고_이미지를_보여준다`() {
        onView(withId(R.id.recycler_view_reservation_home))
            .perform(
                RecyclerViewActions.scrollToHolder(
                    instanceOf(AdvertisementViewHolder::class.java),
                ).atPosition(FIRST_ITEM_POSITION),
            )

        onView(withId(R.id.image_view_item_advertisement)).check(matches(isDisplayed()))
    }

    private fun matchViewHolderAtPosition(
        position: Int,
        viewHolderClass: Class<out RecyclerView.ViewHolder>,
    ): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Checking ViewHolder at position $position")
            }

            override fun matchesSafely(view: View): Boolean {
                if (view !is RecyclerView) return false
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                return viewHolder != null &&
                    viewHolderClass.isInstance(
                        viewHolder,
                    )
            }
        }
    }

    companion object {
        const val FIRST_ITEM_POSITION = 0
        const val THIRD_ITEM_POSITION = 2
        const val FOURTH_ITEM_POSITION = 3
    }
}

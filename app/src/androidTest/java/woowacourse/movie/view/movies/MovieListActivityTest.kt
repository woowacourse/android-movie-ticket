package woowacourse.movie.view.movies

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R

@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieListActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @Test
    fun `리사이클러뷰가_화면에_출력된다`() {
        onView(withId(R.id.rv)).check(matches(isDisplayed()))
    }

    @Test
    fun `리사이클러뷰의_4번쨰_영화_제목은_해리_포터와_죽음의_성물_2부다`() {
        onView(withId(R.id.rv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(15))

        onView(withText("해리 포터와 죽음의 성물 2부")).check(matches(isDisplayed()))
    }
}

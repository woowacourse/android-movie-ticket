package woowacourse.movie

import android.widget.ListView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.model.ScreenMovieUiModel

class ScreeningMovieActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(ScreeningMovieActivity::class.java)

    @Before
    fun setUp() {
        activityRule.scenario.onActivity { activity ->
            val listView = activity.findViewById<ListView>(R.id.list_view)
            val items = listOf(
                ScreenMovieUiModel(
                    1,
                    title = "해리 포터와 마법사의 돌",
                    R.drawable.img_movie_poster,
                    "상영일: 2024.3.1",
                    "러닝타임: 152분",
                ),
                ScreenMovieUiModel(
                    2,
                    title = "해리 포터와 마법사의 돌",
                    R.drawable.img_movie_poster,
                    "상영일: 2024.3.2",
                    "러닝타임: 162분",
                )
            )
            listView.adapter = MovieAdapter(activity, items)
        }
    }


    @Test
    fun `Activity가_실행되면_뷰가_보인다`() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun `listView가_만들어지면_itemValue들이_view의_text로_배치된다`() {
        onData(`is`(withItemContent(containsString("해리 포터와 마법사의 돌"))))
            .inAdapterView(withId(R.id.list_view))
            .atPosition(0)
            .onChildView(withId(R.id.tv_movie_running_time))
            .check(matches(withText("러닝타임: 152분")))
    }

    private fun withItemContent(itemTextMatcher: Matcher<String>): Matcher<ScreenMovieUiModel> {
        return object : TypeSafeMatcher<ScreenMovieUiModel>(ScreenMovieUiModel::class.java) {
            override fun matchesSafely(screenMovieUiModel: ScreenMovieUiModel): Boolean {
                return itemTextMatcher.matches(screenMovieUiModel.title)
            }

            override fun describeTo(description: Description) {
                description.appendText("with item content matching: ")
                itemTextMatcher.describeTo(description)
            }
        }
    }
}

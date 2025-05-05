package woowacourse.movie.feature

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.feature.movies.view.MoviesActivity

@Suppress("ktlint:standard:function-naming")
class MoviesActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MoviesActivity::class.java)

    @Test
    fun `화면에_영화_제목이_출력된다`() {
        onView(
            allOf(
                withId(R.id.tv_movie_title),
                isDescendantOfA(nthChildOf(withId(R.id.rv_movies), 0)),
            ),
        ).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    fun nthChildOf(
        parentMatcher: Matcher<View>,
        childPosition: Int,
    ): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) = Unit

            override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup &&
                    parentMatcher.matches(parent) &&
                    parent.getChildAt(childPosition) == view
            }
        }
    }
}

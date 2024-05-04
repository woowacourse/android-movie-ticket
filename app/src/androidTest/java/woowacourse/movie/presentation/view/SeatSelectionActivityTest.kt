package woowacourse.movie.presentation.view

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import java.util.regex.Pattern

class SeatSelectionActivityTest {
    private val testContext = ApplicationProvider.getApplicationContext<Context>()
    private val intent: Intent = seatSelectionActivityIntent(testContext)

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule<SeatSelectionActivity>(intent)

    @Test
    fun `좌석_배치도가_나타난다`() {
        onView(withId(R.id.seatingChartLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun `선택한_영화의_제목이_나타난다`() {
        onView(withId(R.id.movieTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun `확인_버튼이_나타난다`() {
        onView(withId(R.id.confirmButton)).check(matches(isDisplayed()))
    }

    @Test
    fun `좌석을_선택하면_선택한_좌석의_배경_색이_노란색으로_바뀐다`() {
        // when
        onView(
            nthChildOf(
                nthChildOf(
                    withId(R.id.seatingChartLayout),
                    2,
                ),
                3,
            ),
        ).perform(click())

        // then
        onView(
            nthChildOf(
                nthChildOf(
                    withId(R.id.seatingChartLayout),
                    2,
                ),
                3,
            ),
        ).check(matches(withBackgroundColor(R.color.yellow)))
    }

    @Test
    fun `선택된_좌석을_한번_더_선택하면_배경_색이_흰색으로_돌아온다`() {
        // given
        onView(
            nthChildOf(
                nthChildOf(
                    withId(R.id.seatingChartLayout),
                    2,
                ),
                3,
            ),
        ).perform(click())

        // when
        onView(
            nthChildOf(
                nthChildOf(
                    withId(R.id.seatingChartLayout),
                    2,
                ),
                3,
            ),
        ).perform(click())

        // then
        onView(
            nthChildOf(
                nthChildOf(
                    withId(R.id.seatingChartLayout),
                    2,
                ),
                3,
            ),
        ).check(matches(withBackgroundColor(R.color.white)))
    }

    @Test
    fun `처음에는_확인_버튼이_비활성화_되어_있다`() {
        onView(withId(R.id.confirmButton))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun `예매_수량만큼_좌석을_선택해야_확인_버튼이_활성화된다`() {
        // when
        onView(
            nthChildOf(
                nthChildOf(
                    withId(R.id.seatingChartLayout),
                    2,
                ),
                3,
            ),
        ).perform(click())

        // then
        onView(withId(R.id.confirmButton))
            .check(matches(isEnabled()))
    }

    @Test
    fun `좌석_선택_시_선택한_좌석의_가격의_합을_보여준다`() {
        // when
        onView(
            nthChildOf(
                nthChildOf(
                    withId(R.id.seatingChartLayout),
                    2,
                ),
                3,
            ),
        ).perform(click())

        // then
        onView(withId(R.id.totalPrice))
            .check(matches(withTextPatternMatching(Pattern.compile("^\\d{1,3}(,\\d{3})*원$"))))
    }

    private fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with $childPosition child view")
            }

            override fun matchesSafely(item: View): Boolean {
                if (item.parent !is ViewGroup) {
                    return parentMatcher.matches(item)
                }

                val group = item.parent as ViewGroup
                return parentMatcher.matches(item.parent) && group.getChildAt(childPosition) == item
            }
        }
    }

    private fun withBackgroundColor(colorRes: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with background color: ")
                description.appendValue(colorRes)
            }

            override fun matchesSafely(item: View): Boolean {
                val backgroundColor = (item.background as? ColorDrawable)?.color
                return backgroundColor == item.context.resources.getColor(colorRes, null)
            }
        }
    }

    private fun withTextPatternMatching(pattern: Pattern): TypeSafeMatcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with text matching regex: ${pattern.pattern()}")
            }

            override fun matchesSafely(item: View): Boolean {
                if (item !is TextView) return false
                return pattern.matcher(item.text.toString()).matches()
            }
        }
    }
}

package woowacourse.movie.seatselection.view

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_COUNT
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_ID

@RunWith(AndroidJUnit4::class)
class MovieSeatSelectionTest {
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieSeatSelectionActivity::class.java,
        ).apply {
            putExtra(KEY_MOVIE_ID, 0L)
            putExtra(KEY_MOVIE_COUNT, 3)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieSeatSelectionActivity>(intent)

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val bGradeColor = ContextCompat.getColor(context, R.color.b_grade)
    private val sGradeColor = ContextCompat.getColor(context, R.color.s_grade)
    private val aGradeColor = ContextCompat.getColor(context, R.color.a_grade)
    private val unSelectedColor = ContextCompat.getColor(context, R.color.white)
    private val selectedColor = ContextCompat.getColor(context, R.color.selected)

    @Test
    fun `예매할_영화의_제목이_표시된다`() {
        onView(withId(R.id.seatTitle))
            .check(matches(withText("타이타닉 0")))
    }

    @Test
    fun `선택한_좌석들의_총_가격이_표시된다`() {
        onView(withId((R.id.A1))).perform(click())
        onView(withId((R.id.C1))).perform(click())
        onView(withId((R.id.E1))).perform(click())

        onView(withId(R.id.seatPrice))
            .check(matches(withText("37,000")))
    }

    @Test
    fun `1열과_2열의_좌석_색상은_보라색이다`() {
        onView(withId((R.id.rowA)))
            .check(matches(withTextColorForRow(bGradeColor)))
        onView(withId((R.id.rowB)))
            .check(matches(withTextColorForRow(bGradeColor)))
    }

    @Test
    fun `3열과_4열의_좌석_색상은_초록색이다`() {
        onView(withId((R.id.rowC)))
            .check(matches(withTextColorForRow(sGradeColor)))
        onView(withId((R.id.rowD)))
            .check(matches(withTextColorForRow(sGradeColor)))
    }

    @Test
    fun `5열의_좌석_색상은_파란색이다`() {
        onView(withId((R.id.rowE)))
            .check(matches(withTextColorForRow(aGradeColor)))
    }

    @Test
    fun `선택된_좌석의_배경색이_노란색으로_변경된다`() {
        onView(withId((R.id.A1)))
            .perform(click())
            .check(matches(withBackgroundColor(selectedColor)))
    }

    @Test
    fun `이미_선택된_좌석이라면_배경색이_노란색에서_하얀색으로_변경된다`() {
        onView(withId((R.id.A1)))
            .perform(click())
            .perform(click())
            .check(matches(withBackgroundColor(unSelectedColor)))
    }

    @Test
    fun `선택_가능한_좌석_개수만큼_좌석이_선택되었을_경우에는_확인_버튼이_클릭이_가능하다`() {
        onView(withId((R.id.A1))).perform(click())
        onView(withId((R.id.C1))).perform(click())
        onView(withId((R.id.E1))).perform(click())

        onView(withId(R.id.completeBtn)).check(matches(isEnabled()))
    }

    @Test
    fun `선택_가능한_좌석_개수만큼_좌석이_선택되지_않았을_경우에는_확인_버튼이_클릭이_불가능하다`() {
        onView(withId((R.id.A1))).perform(click())
        onView(withId((R.id.C1))).perform(click())

        onView(withId(R.id.completeBtn)).check(matches(not(isEnabled())))
    }

    @Test
    fun `확인_버튼을_클릭하면_다이얼로그_화면이_나타난다`() {
        onView(withId((R.id.A1))).perform(click())
        onView(withId((R.id.C1))).perform(click())
        onView(withId((R.id.E1))).perform(click())
        onView(withId(R.id.completeBtn)).perform(click())

        onView(withText("예매 확인")).check(matches(isDisplayed()))
    }

    @Test
    fun `좌석_선택_후_화면_회전_시에도_선택했던_좌석_데이터는_유지된다`() {
        onView(withId((R.id.A1))).perform(click())
        onView(withId((R.id.C1))).perform(click())
        onView(withId((R.id.E1))).perform(click())

        val activityScenario = activityRule.scenario
        activityScenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.A1)).check(matches(withBackgroundColor(selectedColor)))
        onView(withId(R.id.C1)).check(matches(withBackgroundColor(selectedColor)))
        onView(withId(R.id.E1)).check(matches(withBackgroundColor(selectedColor)))
    }

    private fun withTextColorForRow(expectedColor: Int): Matcher<View> {
        return object : BoundedMatcher<View, TableRow>(TableRow::class.java) {
            override fun describeTo(description: Description?) {}

            override fun matchesSafely(item: TableRow?): Boolean {
                if (item == null) return false
                for (i in 0 until item.childCount) {
                    val textView = item.getChildAt(i) as? TextView ?: return false
                    if (textView.currentTextColor != expectedColor) return false
                }
                return true
            }
        }
    }

    private fun withBackgroundColor(expectedColor: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description?) {}

            override fun matchesSafely(item: TextView?): Boolean {
                val backgroundColor = (item?.background as? ColorDrawable)?.color ?: 0
                return backgroundColor == expectedColor
            }
        }
    }
}

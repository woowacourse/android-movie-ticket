package woowacourse.movie

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.support.test.uiautomator.UiDevice
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SeatSelectionActivityTest {
    @get:Rule
    val activityScenarioRule =
        ActivityScenarioRule<SeatSelectionActivity>(
            Intent(
                ApplicationProvider.getApplicationContext(),
                SeatSelectionActivity::class.java,
            ).apply {
                putExtra("movie_id", 0L)
                putExtra("number_of_people", 4)
            },
        )

    @Test
    fun `영화_제목과_좌석_배치도가_올바르게_표출된다`() {
        onView(withId(R.id.tv_movie_title)).check(matches(withText("해리 포터와 마법사의 돌")))
        onView(withId(R.id.tl_screens)).check(matches(hasChildCount(5)))
    }

    @Test
    fun `특정_좌석을_선택하면_배경색이_선택_색상으로_변경된다`() {
        activityScenarioRule.scenario.onActivity {
            val item =
                it.findViewById<TableLayout>(R.id.tl_screens).children.filterIsInstance<TableRow>()
                    .first().children.filterIsInstance<TextView>().first()
            item.performClick()
            val color = (item.background as ColorDrawable).color
            assertEquals(color, ContextCompat.getColor(it, R.color.yellow))
        }
    }

    @Test
    fun `특정_좌석_선택을_취소하면_미선택_색상으로_변경된다`() {
        activityScenarioRule.scenario.onActivity {
            val item =
                it.findViewById<TableLayout>(R.id.tl_screens).children.filterIsInstance<TableRow>()
                    .first().children.filterIsInstance<TextView>().first()
            item.performClick()
            item.performClick()
            val color = (item.background as ColorDrawable).color
            assertEquals(color, ContextCompat.getColor(it, R.color.white))
        }
    }

    @Test
    fun `화면이_회전되어도_좌석_선택_정보가_유지된다`() {
    }

    @Test
    fun `인원수에_맞는_좌석_수만큼_선택되면_더_이상_다른_좌석이_선택되지_않는다`() {
        activityScenarioRule.scenario.onActivity {
            val firstRowItems =
                it.findViewById<TableLayout>(R.id.tl_screens)
                    .children
                    .filterIsInstance<TableRow>()
                    .first()
                    .children
                    .filterIsInstance<TextView>()

            firstRowItems.forEach { textView ->
                textView.performClick()
            }
            val lastItem =
                it.findViewById<TableLayout>(R.id.tl_screens)
                    .children
                    .filterIsInstance<TableRow>()
                    .last()
                    .children
                    .filterIsInstance<TextView>()
                    .last()

            lastItem.performClick()
            val color = (lastItem.background as ColorDrawable).color
            assertEquals(color, ContextCompat.getColor(lastItem.context, R.color.white))
        }
    }

    @Test
    fun `인원수에_맞는_좌석_수만큼_선택되면_확인_버튼의_색상이_활성화_색상으로_변경된다`() {
        performMultipleSeatsSelection()
        onView(withId(R.id.btn_complete_reservation)).check { view, _ ->
            val actualColor = (view.background as ColorDrawable).color
            assertEquals(actualColor, ContextCompat.getColor(view.context, R.color.purple_500))
        }
    }

    @Test
    fun `확인_버튼_클릭_시_예약_확정_다이얼로그가_표출된다`() {
        performMultipleSeatsSelection()
        onView(withId(R.id.btn_complete_reservation)).perform(click())
        onView(withText("예매 확인")).check(matches(isDisplayed()))
        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
        onView(withId(android.R.id.button1)).check(matches(isDisplayed()))
        onView(withId(android.R.id.button2)).check(matches(isDisplayed()))
    }

    @Test
    fun `예약_확정_다이얼로그에서_취소_버튼_클릭_시_다이얼로그가_사라진다`() {
        performMultipleSeatsSelection()
        onView(withId(R.id.btn_complete_reservation)).perform(click())
        onView(withId(android.R.id.button2)).perform(click())
        onView(withText("예매 확인")).check(doesNotExist())
        onView(withText("정말 예매하시겠습니까?")).check(doesNotExist())
        onView(withId(android.R.id.button1)).check(doesNotExist())
        onView(withId(android.R.id.button2)).check(doesNotExist())
    }

    @Test
    fun `예약_확정_다이얼로그_이외의_영역_클릭_시에도_다이얼로그가_사라지지_않는다`() {
        performMultipleSeatsSelection()
        onView(withId(R.id.btn_complete_reservation)).perform(click())
        val device = UiDevice.getInstance(getInstrumentation())
        device.click(0, 0)
        onView(withText("예매 확인")).check(matches(isDisplayed()))
        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
        onView(withId(android.R.id.button1)).check(matches(isDisplayed()))
        onView(withId(android.R.id.button2)).check(matches(isDisplayed()))
    }

    private fun performMultipleSeatsSelection() {
        activityScenarioRule.scenario.onActivity {
            val firstRowItems =
                it.findViewById<TableLayout>(R.id.tl_screens)
                    .children
                    .filterIsInstance<TableRow>()
                    .first()
                    .children
                    .filterIsInstance<TextView>()

            val lastRowItems =
                it.findViewById<TableLayout>(R.id.tl_screens)
                    .children
                    .filterIsInstance<TableRow>()
                    .last()
                    .children
                    .filterIsInstance<TextView>()

            firstRowItems.first().performClick()
            firstRowItems.last().performClick()
            lastRowItems.first().performClick()
            lastRowItems.last().performClick()
        }
    }
}

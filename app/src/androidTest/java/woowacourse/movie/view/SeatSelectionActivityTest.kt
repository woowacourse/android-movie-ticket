package woowacourse.movie.view

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
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R

@RunWith(AndroidJUnit4::class)
class SeatSelectionActivityTest {
    @get:Rule
    val activityScenarioRule =
        ActivityScenarioRule<SeatSelectionActivity>(
            Intent(
                ApplicationProvider.getApplicationContext(),
                SeatSelectionActivity::class.java,
            ).apply {
                putExtra("screening_id", 0L)
                putExtra("count", 4)
                putExtra("movie_date", "2024-04-01")
                putExtra("movie_time", "11:00")
                putExtra("movie_title", "해리 포터와 마법사의 돌")
            },
        )

    private lateinit var device: UiDevice
    private lateinit var seatItems: Sequence<TextView>

    @Before
    fun setUp() {
        device = UiDevice.getInstance(getInstrumentation())
        activityScenarioRule.scenario.onActivity {
            seatItems =
                it.findViewById<TableLayout>(R.id.tl_seats)
                    .children
                    .filterIsInstance<TableRow>()
                    .first()
                    .children
                    .filterIsInstance<TextView>()
        }
    }

    @Test
    fun `영화_제목과_좌석_배치도가_올바르게_표출된다`() {
        onView(withId(R.id.tv_movie_title)).check(matches(withText("해리 포터와 마법사의 돌")))
        onView(withId(R.id.tl_seats)).check(matches(hasChildCount(5)))
    }

    @Test
    fun `특정_좌석을_선택하면_배경색이_선택_색상으로_변경된다`() {
        activityScenarioRule.scenario.onActivity {
            val item = seatItems.first()
            item.performClick()

            val color = (item.background as ColorDrawable).color
            assertEquals(color, ContextCompat.getColor(it, R.color.yellow))
        }
    }

    @Test
    fun `특정_좌석_선택을_취소하면_미선택_색상으로_변경된다`() {
        activityScenarioRule.scenario.onActivity {
            val item = seatItems.first()
            repeat(2) { item.performClick() }

            val color = (item.background as ColorDrawable).color
            assertEquals(color, ContextCompat.getColor(it, R.color.white))
        }
    }

    @Test
    fun `화면이_회전되어도_좌석_선택_정보가_유지된다`() {
        // TODO
    }

    @Test
    fun `인원수에_맞는_좌석_수만큼_선택되면_더_이상_다른_좌석이_선택되지_않는다`() {
        activityScenarioRule.scenario.onActivity {
            performMultipleSeatsSelection()
            val lastItem =
                it.findViewById<TableLayout>(R.id.tl_seats)
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
        checkDialogExists()
    }

    @Test
    fun `예약_확정_다이얼로그에서_취소_버튼_클릭_시_다이얼로그가_사라진다`() {
        performMultipleSeatsSelection()
        onView(withId(R.id.btn_complete_reservation)).perform(click())
        onView(withId(android.R.id.button2)).perform(click())
        checkDialogDoesNotExist()
    }

    @Test
    fun `예약_확정_다이얼로그_이외의_영역_중_좌상단_클릭_시_다이얼로그가_사라지지_않는다`() {
        performMultipleSeatsSelection()
        onView(withId(R.id.btn_complete_reservation)).perform(click())
        device.click(0, 0)
        checkDialogExists()
    }

    @Test
    fun `예약_확정_다이얼로그_이외의_영역_중_우상단_클릭_시_다이얼로그가_사라지지_않는다`() {
        performMultipleSeatsSelection()
        onView(withId(R.id.btn_complete_reservation)).perform(click())
        device.click(device.displayWidth, 0)
        checkDialogExists()
    }

    @Test
    fun `예약_확정_다이얼로그_이외의_영역_중_좌하단_클릭_시_다이얼로그가_사라지지_않는다`() {
        performMultipleSeatsSelection()
        onView(withId(R.id.btn_complete_reservation)).perform(click())
        device.click(0, device.displayHeight)
        checkDialogExists()
    }

    @Test
    fun `예약_확정_다이얼로그_이외의_영역_중_우하단_클릭_시_다이얼로그가_사라지지_않는다`() {
        performMultipleSeatsSelection()
        onView(withId(R.id.btn_complete_reservation)).perform(click())
        device.click(device.displayWidth, device.displayHeight)
        checkDialogExists()
    }

    private fun checkDialogExists() {
        onView(withText("예매 확인")).check(matches(isDisplayed()))
        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
        onView(withId(android.R.id.button1)).check(matches(isDisplayed()))
        onView(withId(android.R.id.button2)).check(matches(isDisplayed()))
    }

    private fun checkDialogDoesNotExist() {
        onView(withText("예매 확인")).check(doesNotExist())
        onView(withText("정말 예매하시겠습니까?")).check(doesNotExist())
        onView(withId(android.R.id.button1)).check(doesNotExist())
        onView(withId(android.R.id.button2)).check(doesNotExist())
    }

    private fun performMultipleSeatsSelection() {
        activityScenarioRule.scenario.onActivity {
            val lastRowItems =
                it.findViewById<TableLayout>(R.id.tl_seats)
                    .children
                    .filterIsInstance<TableRow>()
                    .last()
                    .children
                    .filterIsInstance<TextView>()

            seatItems.first().performClick()
            seatItems.last().performClick()
            lastRowItems.first().performClick()
            lastRowItems.last().performClick()
        }
    }
}

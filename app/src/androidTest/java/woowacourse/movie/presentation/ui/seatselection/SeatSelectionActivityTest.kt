package woowacourse.movie.presentation.ui.seatselection

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.presentation.utils.getDummyReservationInfo

@RunWith(AndroidJUnit4::class)
class SeatSelectionActivityTest {
    @get:Rule
    val activityRule: ActivityScenarioRule<SeatSelectionActivity> =
        ActivityScenarioRule<SeatSelectionActivity>(
            Intent(
                ApplicationProvider.getApplicationContext(),
                SeatSelectionActivity::class.java,
            ).apply {
                val reservationInfo = getDummyReservationInfo()
                putExtra("reservationInfo", reservationInfo)
            },
        )

    @Test
    fun `선택되지_않은_좌석을_선택했을_때_선택한_좌석_자리의_색상이_초기_색상과_달라진다`() {
        performSeatsSelection { _, view ->
            val expected = (view.background as ColorDrawable).color
            view.performClick()
            val actual = (view.background as ColorDrawable).color
            assertNotEquals(expected, actual)
        }
    }

    @Test
    fun `이미_선택된_좌석을_선택했을_때_선택한_좌석_자리의_색상이_초기_색상으로_바뀐다`() {
        performSeatsSelection { _, view ->
            val expected = (view.background as ColorDrawable).color
            view.performClick()
            view.performClick()
            val actual = (view.background as ColorDrawable).color
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `선택해야_하는_좌석의_개수가_4일때_4개의_좌석을_선택하면_좌석_선택_버튼이_활성화_된다`() {
        // given
        performSeatsSelection(selected = listOf(0, 1, 2, 3))

        // when & then
        onView(withId(R.id.btn_done)).check(matches(isEnabled()))
    }

    @Test
    fun `선택해야_하는_좌석의_개수가_4일때_3개의_좌석을_선택하면_좌석_선택_버튼이_활성화_되지_않는다`() {
        // given
        performSeatsSelection(selected = listOf(0, 1, 2))

        // when & then
        onView(withId(R.id.btn_done)).check(matches(not(isEnabled())))
    }

    @Test
    fun `좌석_선택_버튼이_활성화_되었을_때_버튼을_누르면_다이얼로그가_나타난다`() {
        // given
        performSeatsSelection(selected = listOf(0, 1, 2, 3))

        // when
        onView(withId(R.id.btn_done)).perform(click())

        // then
        checkReservationDialogExists()
    }

    @Test
    fun `예약_확정_다이얼로그에서_취소_버튼_클릭_시_다이얼로그가_사라진다`() {
        // given
        performSeatsSelection(selected = listOf(0, 1, 2, 3))

        // when
        onView(withId(R.id.btn_done)).perform(click())
        onView(withId(android.R.id.button2)).perform(click())

        // then
        checkReservationDialogDoesNotExist()
    }

    private fun checkReservationDialogExists() {
        onView(withText(R.string.dialog_reservation_title)).check(matches(isDisplayed()))
        onView(withText(R.string.dialog_reservation_message)).check(matches(isDisplayed()))
        onView(withId(android.R.id.button1)).check(matches(isDisplayed()))
        onView(withId(android.R.id.button2)).check(matches(isDisplayed()))
    }

    private fun checkReservationDialogDoesNotExist() {
        onView(withText(R.string.dialog_reservation_title)).check(doesNotExist())
        onView(withText(R.string.dialog_reservation_message)).check(doesNotExist())
        onView(withId(android.R.id.button1)).check(doesNotExist())
        onView(withId(android.R.id.button2)).check(doesNotExist())
    }

    private fun performSeatsSelection(action: (context: Context, view: TextView) -> Unit) {
        activityRule.scenario.onActivity { context ->
            val view =
                context.findViewById<TableLayout>(R.id.tl_seat_board).children.filterIsInstance<TableRow>()
                    .flatMap { row -> row.children }.filterIsInstance<TextView>().first()
            action(context, view)
        }
    }

    private fun performSeatsSelection(selected: List<Int>) {
        activityRule.scenario.onActivity { context ->
            val view =
                context.findViewById<TableLayout>(R.id.tl_seat_board).children.filterIsInstance<TableRow>()
                    .flatMap { row -> row.children }.filterIsInstance<TextView>().toList()
            selected.forEach { position -> view[position].performClick() }
        }
    }
}

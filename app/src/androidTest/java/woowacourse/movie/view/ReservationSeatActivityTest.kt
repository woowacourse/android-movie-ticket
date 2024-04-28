package woowacourse.movie.view

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.data.MovieDao
import woowacourse.movie.model.Ticket
import woowacourse.movie.presentation.reservation.seat.ReservationSeatActivity
import woowacourse.movie.presentation.reservation.seat.ReservationSeatActivity.Companion.DIALOG_MESSAGE
import woowacourse.movie.presentation.reservation.seat.ReservationSeatActivity.Companion.DIALOG_NEGATIVE
import woowacourse.movie.presentation.reservation.seat.ReservationSeatActivity.Companion.DIALOG_POSITIVE
import woowacourse.movie.presentation.reservation.seat.ReservationSeatActivity.Companion.DIALOG_TITLE
import woowacourse.movie.presentation.screen.detail.MovieDetailActivity.Companion.TICKET

@RunWith(AndroidJUnit4::class)
class ReservationSeatActivityTest {
    private val movie = MovieDao().find(0)
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            ReservationSeatActivity::class.java,
        ).putExtra("title", movie.title)
            .putExtra(TICKET, Ticket(3))

    @get:Rule
    val activityRule = ActivityScenarioRule<ReservationSeatActivity>(intent)
    private val context = getInstrumentation().targetContext
    private val unSelectedColor = ContextCompat.getColor(context, R.color.white)
    private val selectedColor = ContextCompat.getColor(context, R.color.yellow)
    private val device = UiDevice.getInstance(getInstrumentation())

    @Test
    fun `액티비티가_시작하면_예약중인_영화의_title이_보인다`() {
        onView(withId(R.id.seat_title_textview))
            .check(matches(withText(movie.title)))
    }

    @Test
    fun `액티비티가_시작하면_지불금액이_보인다`() {
        onView(withId(R.id.seat_selection_price))
            .check(matches(withText("0원")))
    }

    @Test
    fun `특정좌석을_한번_클릭하면_배경색이_선택된_색상으로_변경된다`() {
        onView(withId(R.id.seat_tv_item10))
            .perform(click())
            .check(matches(withBackgroundColor(selectedColor)))
    }

    @Test
    fun `특정좌석을_두번_클릭하면_배경색이_선택되지않은_색상으로_변경된다`() {
        onView(withId(R.id.seat_tv_item10))
            .perform(click())
            .perform(click())
            .check(matches(withBackgroundColor(unSelectedColor)))
    }

    @Test
    fun `티켓의_수_만큼_좌석을_선택하고_확인버튼을_클릭하면_다이얼로그가_표시된다`() {
        onView(withId(R.id.seat_tv_item2)).perform(click())
        onView(withId(R.id.seat_tv_item10)).perform(click())
        onView(withId(R.id.seat_tv_item18)).perform(click())

        onView(withId(R.id.reservation_complete_linear_layout))
            .perform(click())

        checkDialogExists()
    }

    @Test
    fun `티켓의_수_만큼_좌석을_선택하지않고_확인버튼을_클릭하면_다이얼로그가_표시되지않는다`() {
        onView(withId(R.id.seat_tv_item2)).perform(click())

        onView(withId(R.id.reservation_complete_linear_layout))
            .perform(click())

        checkDialogDoesNotExist()
    }

    @Test
    fun `다이얼로그가_표시된_상태에서_배경을_터치해도_사라지지_않는다`() {
        // given
        onView(withId(R.id.seat_tv_item2)).perform(click())
        onView(withId(R.id.seat_tv_item10)).perform(click())
        onView(withId(R.id.seat_tv_item18)).perform(click())
        onView(withId(R.id.reservation_complete_linear_layout))
            .perform(click())
        checkDialogExists()

        // when
        device.click(0, 0)

        // then
        checkDialogExists()
    }

    @Test
    fun `화면_회전시_선택된_좌석이_유지된다`() {
        // given
        onView(withId(R.id.seat_tv_item2)).perform(click())
        onView(withId(R.id.seat_tv_item10)).perform(click())

        // when
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        // then
        onView(withId(R.id.seat_tv_item2)).check(matches(withBackgroundColor(selectedColor)))
        onView(withId(R.id.seat_tv_item10)).check(matches(withBackgroundColor(selectedColor)))
    }

    @Test
    fun `화면_회전시_선택된_좌석의_가격이_유지된다`() {
        // given
        onView(withId(R.id.seat_tv_item2)).perform(click())
        onView(withId(R.id.seat_tv_item10)).perform(click())
        onView(withId(R.id.seat_selection_price))
            .check(matches(withText("25,000원")))

        // when
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        // then
        onView(withId(R.id.seat_selection_price))
            .check(matches(withText("25,000원")))
    }

    private fun withBackgroundColor(expectedColor: Int): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("with background color: $expectedColor")
            }

            override fun matchesSafely(item: View?): Boolean {
                val backgroundColor = (item?.background as? ColorDrawable)?.color ?: 0
                return backgroundColor == expectedColor
            }
        }
    }

    private fun checkDialogExists() {
        onView(withText(DIALOG_TITLE)).check(matches(isDisplayed()))
        onView(withText(DIALOG_MESSAGE)).check(matches(isDisplayed()))
        onView(withText(DIALOG_POSITIVE)).check(matches(isDisplayed()))
        onView(withText(DIALOG_NEGATIVE)).check(matches(isDisplayed()))
    }

    private fun checkDialogDoesNotExist() {
        onView(withText(DIALOG_TITLE)).check(doesNotExist())
        onView(withText(DIALOG_MESSAGE)).check(doesNotExist())
        onView(withText(DIALOG_POSITIVE)).check(doesNotExist())
        onView(withText(DIALOG_NEGATIVE)).check(doesNotExist())
    }
}

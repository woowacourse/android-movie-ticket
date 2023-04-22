package woowacourse.movie

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import junit.framework.TestCase.assertTrue
import org.hamcrest.Matchers.not
import org.junit.Test
import woowacourse.movie.SeatSelectionActivityTestUtil.SeatSelectionInfo
import woowacourse.movie.SeatSelectionActivityTestUtil.scenario
import woowacourse.movie.SeatSelectionActivityTestUtil.startTest

class SeatSelectionActivityTest {

    @Test
    fun `좌석_선택에_필요한_정보가_전달되지_않으면_액티비티가_바로_종료된다`() {
        // given
        val seatSelectionInfo = null

        // when
        startTest(seatSelectionInfo)

        // then
        assertTrue(scenario.state == Lifecycle.State.DESTROYED)
    }

    @Test
    fun `클릭한_좌석이_선택된_상태로_바뀐다`() {
        // given
        startTest(SeatSelectionInfo(seatCount = 1))
        val seat = onView(withText("A1"))

        // when
        seat.perform(click())

        // then
        seat.check(matches(isSelected()))
    }

    @Test
    fun `선택한_좌석을_다시_클릭하면_선택되지_않은_상태로_바뀐다`() {
        // given
        startTest(SeatSelectionInfo(seatCount = 1))
        val seat = onView(withText("A1"))
        seat.perform(click())

        // when
        seat.perform(click())

        // then
        seat.check(matches(not(isSelected())))
    }

    @Test
    fun `선택할_수_있는_좌석의_개수만큼_선택하면_완료_버튼이_클릭_가능한_상태로_바뀐다`() {
        // given
        startTest(SeatSelectionInfo(seatCount = 2))

        // when
        onView(withText("A1")).perform(click())
        onView(withText("B1")).perform(click())

        // then
        val completeButton = onView(withId(R.id.seat_selection_complete_button))
        completeButton.check(matches(isEnabled()))
    }

    @Test
    fun `선택할_수_있는_좌석의_개수만큼_선택하지_않은_경우_완료_버튼은_클릭_가능하지_않은_상태이다`() {
        // given
        startTest(SeatSelectionInfo(seatCount = 1))

        // when

        // then
        val completeButton = onView(withId(R.id.seat_selection_complete_button))
        completeButton.check(matches(not(isEnabled())))
    }

    @Test
    fun `여러_좌석을_클릭하면_두_좌석_가격의_합으로_갱신된다`() {
        // given
        startTest(SeatSelectionInfo(seatCount = 2))
        val seatOfRateA = onView(withText("E1"))
        val seatOfRateB = onView(withText("A1"))

        // when
        seatOfRateA.perform(click())
        seatOfRateB.perform(click())
        val paymentAmount = onView(withId(R.id.seat_selection_payment_amount_text))

        // then
        paymentAmount.check(matches(withText("22000")))
    }
}

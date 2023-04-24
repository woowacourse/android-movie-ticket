package woowacourse.movie.presentation

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotSelected
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.presentation.choiceSeat.ChoiceSeatActivity
import woowacourse.movie.presentation.model.ReservationModel
import java.time.LocalDateTime

class ChoiceSeatActivityTest {

    private val reservation =
        ReservationModel(
            movieId = 1L,
            bookedDateTime = LocalDateTime.of(2024, 3, 1, 9, 0),
            count = 2,
        )

    private val intent =
        Intent(ApplicationProvider.getApplicationContext(), ChoiceSeatActivity::class.java).apply {
            this.putExtra("RESERVATION", reservation)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<ChoiceSeatActivity>(intent)

    @Test
    fun 현재_영화_이름을_확인한다() {
        // given
        val title = onView(withId(R.id.textChoiceTitle))

        // then
        title.check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun 좌석을_선택하지_않으면_가격은_0원이다() {
        // given
        val paymentAmount = onView(withId(R.id.textChoicePaymentAmount))

        // then
        paymentAmount.check(matches(withText("0원")))
    }

    @Test
    fun A1_좌석을_클릭하면_A1이_선택된다() {
        // given
        val seat = onView(withText("A1"))

        // when
        seat.perform(click())

        // then
        seat.check(matches(isSelected()))
    }

    @Test
    fun 클릭된_A1_좌석을_다시_클릭하면_A1이_선택해제된다() {
        // given
        val seat = onView(withText("A1")).perform(click())

        // when
        seat.perform(click())

        // then
        seat.check((matches(isNotSelected())))
    }

    @Test
    fun 좌석을_선택하지_않으면_확인_버튼이_비활성화_된다() {
        // given
        val confirmButton = onView(withId(R.id.buttonChoiceConfirm))

        // then
        confirmButton.check((matches(isNotEnabled())))
    }

    @Test
    fun 티켓_개수가_2일때_1개만_선택하면_확인_버튼이_비활성화_된다() {
        // given
        val seatA1 = onView(withText("A1"))
        val confirmButton = onView(withId(R.id.buttonChoiceConfirm))

        // when
        seatA1.perform(click())

        // then
        confirmButton.check((matches(isNotEnabled())))
    }

    @Test
    fun 티켓_개수가_2면_좌석_2개일때_확인_버튼이_활성화_된다() {
        // given
        val seatA1 = onView(withText("A1"))
        val seatB1 = onView(withText("B1"))
        val confirmButton = onView(withId(R.id.buttonChoiceConfirm))

        // when
        seatA1.perform(click())
        seatB1.perform(click())

        // then
        confirmButton.check((matches(isEnabled())))
    }

    @Test
    fun 티켓_개수가_2면_이미_2개가_선택됐을_때_더_이상_선택할_수_없다() {
        // given
        onView(withText("A1")).perform(click())
        onView(withText("B1")).perform(click())
        val seatC1 = onView(withText("C1"))

        // when
        seatC1.perform(click())

        // then
        seatC1.check((matches(isNotSelected())))
    }

    @Test
    fun A3을_선택하면_가격은_8000원이다() {
        // given
        val seatA3 = onView(withText("A3"))
        val paymentAmount = onView(withId(R.id.textChoicePaymentAmount))

        // when
        seatA3.perform(click())

        // then
        paymentAmount.check((matches(withText("8,000원"))))
    }

    @Test
    fun C2을_선택하면_가격은_13000원이다() {
        // given
        val seatC2 = onView(withText("C2"))
        val paymentAmount = onView(withId(R.id.textChoicePaymentAmount))

        // when
        seatC2.perform(click())

        // then
        paymentAmount.check((matches(withText("13,000원"))))
    }

    @Test
    fun E2를_선택하면_가격은_10000원이다() {
        // given
        val seatE2 = onView(withText("E2"))
        val paymentAmount = onView(withId(R.id.textChoicePaymentAmount))

        // when
        seatE2.perform(click())

        // then
        paymentAmount.check((matches(withText("10,000원"))))
    }

    @Test
    fun A1_C1_을_선택하면_가격은_21000원이다() {
        // given
        val seatA1 = onView(withText("A1"))
        val seatC1 = onView(withText("C1"))
        val paymentAmount = onView(withId(R.id.textChoicePaymentAmount))

        // when
        seatA1.perform(click())
        seatC1.perform(click())

        // then
        paymentAmount.check((matches(withText("21,000원"))))
    }
}

package woowacourse.movie.presentation

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
import org.hamcrest.Matchers.not
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
        ChoiceSeatActivity.getIntent(ApplicationProvider.getApplicationContext(), reservation)

    @get:Rule
    val activityRule = ActivityScenarioRule<ChoiceSeatActivity>(intent)

    @Test
    fun 현재_영화_이름을_확인한다() {
        // given
        // 해리 포터와 마법사의 돌

        // when & then
        onView(withId(R.id.textChoiceTitle)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun 좌석을_선택하지_않으면_가격은_0원이다() {
        // given
        // nothing

        // when & then
        onView(withId(R.id.textChoicePaymentAmount)).check(matches(withText("0원")))
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
    fun 좌석을_하나_선택하면_가격은_0원이_아니다() {
        // given
        val seatA3 = onView(withText("A3"))
        val paymentAmount = onView(withId(R.id.textChoicePaymentAmount))

        // when
        seatA3.perform(click())

        // then
        paymentAmount.check(matches(not(withText("0원"))))
    }

    @Test
    fun 좌석을_하나_선택하고_같은_좌석을_한번_더_선택하면_가격은_0원이다() {
        // given
        val seatA3 = onView(withText("A3"))
        val paymentAmount = onView(withId(R.id.textChoicePaymentAmount))

        // when
        seatA3.perform(click())
        seatA3.perform(click())

        // then
        paymentAmount.check(matches(withText("0원")))
    }
}

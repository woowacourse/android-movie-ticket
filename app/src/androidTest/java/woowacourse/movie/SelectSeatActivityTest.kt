package woowacourse.movie

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import woowacourse.movie.selectSeat.SelectSeatActivity
import woowacourse.movie.uiModel.TicketUIModel

class SelectSeatActivityTest {
    private lateinit var intent: Intent
    private lateinit var scenario: ActivityScenario<SelectSeatActivity>

    @Before
    fun setupIntent() {
        val ticketUIModel = TicketUIModel("해리 포터와 마법사의 돌", "2025.4.17", "10:00", listOf(), 2, 0)

        intent =
            Intent(
                ApplicationProvider.getApplicationContext(),
                SelectSeatActivity::class.java,
            ).apply {
                putExtra("TICKET", ticketUIModel)
            }

        scenario = ActivityScenario.launch<SelectSeatActivity>(intent)
    }

    @Test
    fun `좌석을_누르면_해당_좌석의_배경이_노란색으로_바뀐다`() {
        onView(withText("A1"))
            .perform(click())
            .check { view: View, noViewFoundException ->
                if (noViewFoundException != null) throw noViewFoundException

                val bg = view.background
                require(bg is ColorDrawable) { "Background is not a ColorDrawable!" }
                val actualColor = (bg as ColorDrawable).color
                val expectedColor = view.context.getColor(R.color.yellow)
                assertEquals("배경색이 노란색이어야 합니다", expectedColor, actualColor)
            }
    }

    @Test
    fun `좌석을_다시_누르면_해당_좌석의_배경이_흰색으로_바뀐다`() {
        onView(withText("A1"))
            .perform(click())
            .perform(click())
            .check { view: View, noViewFoundException ->
                if (noViewFoundException != null) throw noViewFoundException

                val bg = view.background
                require(bg is ColorDrawable) { "Background is not a ColorDrawable!" }
                val actualColor = (bg as ColorDrawable).color
                val expectedColor = view.context.getColor(R.color.white)
                assertEquals("배경색이 흰색이어야 합니다", expectedColor, actualColor)
            }
    }

    @Test
    fun `좌석을_이전_화면에서_지정한_수_이상_선택할려고_할_경우_선택이_불가능하다`() {
        onView(withText("A1"))
            .perform(click())
        onView(withText("A2"))
            .perform(click())
        onView(withText("A3"))
            .perform(click())
            .check { view: View, noViewFoundException ->
                if (noViewFoundException != null) throw noViewFoundException

                val bg = view.background
                require(bg is ColorDrawable) { "Background is not a ColorDrawable!" }
                val actualColor = (bg as ColorDrawable).color
                val expectedColor = view.context.getColor(R.color.white)
                assertEquals("배경색이 흰색이어야 합니다", expectedColor, actualColor)
            }
    }

    @Test
    fun `선택한_좌석에_따라_밑에_금액이_표시된다`() {
        onView(withText("A1"))
            .perform(click())

        onView(withText("10,000원"))
            .check(matches(isDisplayed()))

        onView(withText("C2"))
            .perform(click())

        onView(withText("25,000원"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `선택한_좌석수와_전_화면에서_지정한_좌석수가_일치하지_않으면_예매버튼이_비활성화_된다`() {
        onView(withText("A1"))
            .perform(click())

        onView(withText("예매"))
            .check(matches(isNotEnabled()))

        onView(withText("C2"))
            .perform(click())

        onView(withText("예매"))
            .check(matches(isEnabled()))
    }
}

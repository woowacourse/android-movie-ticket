package woowacourse.movie.uiTest

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import woowacourse.movie.uiTest.fixture.fakeContext
import woowacourse.movie.view.reservation.seat.ReservationSeatActivity
import java.time.LocalDateTime

class ReservationSeatActivityTest {
    private lateinit var scenario: ActivityScenario<ReservationSeatActivity>
    private lateinit var testName: String
    private lateinit var ticket: Ticket

    @get:Rule
    val nameRule = TestName()

    @Before
    fun setUp() {
        testName = nameRule.methodName
        if (testName == "`null값이_Intent된_경우_ErrorDialog를_띄운다`") return
        ticket =
            Ticket(
                "해리 포터와 마법사의 돌",
                LocalDateTime.of(2025, 4, 21, 18, 0),
                2,
            )
        val intent =
            ReservationSeatActivity.newIntent(
                fakeContext,
                ticket,
            )
        scenario = ActivityScenario.launch<ReservationSeatActivity>(intent)
    }

    @Test
    fun `스크린의_위치를_보여준다`() {
        onView(withId(R.id.tv_screen))
            .check(matches(withText("SCREEN")))
    }

    @Test
    fun `좌석을_클릭하면_금액이_증가한다`() {
        onView(withId(R.id.tl_row1))
            .perform(click())

        onView(withId(R.id.reservation_movie_money))
            .check(matches(withText("10,000원")))
    }

    @Test
    fun `예매한_영화의_제목을_보여준다`() {
        onView(withId(R.id.reservation_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `null값이_Intent된_경우_ErrorDialog를_띄운다`() {
        val intent = ReservationSeatActivity.newIntent(fakeContext, null)
        ActivityScenario.launch<ReservationSeatActivity>(intent)

        onView(withText("오류가 생겼습니다.")).check(matches(isDisplayed()))
        onView(withText("잘못된 접근입니다.")).check(matches(isDisplayed()))
        onView(withText("확인")).check(matches(isDisplayed()))
    }
}

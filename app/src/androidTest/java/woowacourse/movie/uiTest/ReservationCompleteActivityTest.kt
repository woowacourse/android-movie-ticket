package woowacourse.movie.uiTest

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import woowacourse.movie.R
import woowacourse.movie.activity.ReservationActivity
import woowacourse.movie.activity.ReservationCompleteActivity
import woowacourse.movie.domain.Ticket
import woowacourse.movie.uiTest.fixture.fakeContext
import java.time.LocalDateTime

class ReservationCompleteActivityTest {
    private lateinit var testName: String

    @get:Rule
    val nameRule = TestName()

    private val ticket =
        Ticket(
            "해리 포터와 마법사의 돌",
            LocalDateTime.of(2025, 4, 21, 18, 0),
            2,
        )

    @Before
    fun setUp() {
        testName = nameRule.methodName
        if (testName == "`null값이_Intent된_경우_ErrorDialog를_띄운다`") return
        val intent = ReservationCompleteActivity.newIntent(fakeContext, ticket)
        ActivityScenario.launch<ReservationCompleteActivity>(intent)
    }

    @Test
    fun `취소_가능_시간_정보_테스트`() {
        onView(withId(R.id.tv_cancel_info))
            .check(matches(withText("영화 상영 시작 15분 전까지\n 취소가 가능합니다.")))
    }

    @Test
    fun `예매한_영화의_제목을_보여준다`() {
        onView(withId(R.id.tv_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `예매한_영화의_상영일을_보여준다`() {
        onView(withId(R.id.tv_movie_date))
            .check(matches(withText("2025.4.21. 18:00")))
    }

    @Test
    fun `예매한_영화의_예매_인원_수를_보여준다`() {
        onView(withId(R.id.tv_movie_personnel))
            .check(matches(withText("일반 2명")))
    }

    @Test
    fun `예매한_영화의_총_티켓_가격을_보여준다`() {
        onView(withId(R.id.tv_movie_total_price))
            .check(matches(withText("26,000원 (현장 결제)")))
    }

    @Test
    fun `null값이_Intent된_경우_ErrorDialog를_띄운다`() {
        val intent = ReservationActivity.newIntent(fakeContext, null)
        ActivityScenario.launch<ReservationCompleteActivity>(intent)

        onView(withText("오류가 생겼습니다.")).check(matches(isDisplayed()))
        onView(withText("잘못된 접근입니다.")).check(matches(isDisplayed()))
        onView(withText("확인")).check(matches(isDisplayed()))
    }
}

package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.fixture.FAKE_CONTEXT
import woowacourse.movie.fixture.TICKET
import woowacourse.movie.view.reservation.model.toUiModel
import woowacourse.movie.view.result.ReservationResultActivity

@Suppress("ktlint:standard:function-naming")
class ReservationResultActivityTest {
    private lateinit var intent: Intent

    @Before
    fun setUp() {
        intent =
            Intent(FAKE_CONTEXT, ReservationResultActivity::class.java).apply {
                putExtra("extra_ticket", TICKET.toUiModel())
            }
        ActivityScenario.launch<ReservationResultActivity>(intent)
    }

    @Test
    fun 예매한_영화의_제목이_표시된다() {
        onView(withId(R.id.movie_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun 예매한_영화의_상영_시간이_표시된다() {
        onView(withId(R.id.showtime)).check(matches(withText("2025.04.15 11:00")))
    }

    @Test
    fun 예매한_인원수가_표시된다() {
        onView(withId(R.id.ticket_info)).check(matches(withText("일반 2명 | B3, D2")))
    }

    @Test
    fun 예매한_총_금액이_표시된다() {
        onView(withId(R.id.total_price)).check(matches(withText("25,000원 (현장 결제)")))
    }
}

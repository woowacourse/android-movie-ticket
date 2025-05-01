package woowacourse.movie.reservation

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Test
import woowacourse.checkMatchText
import woowacourse.movie.MOVIE
import woowacourse.movie.R
import woowacourse.movie.fakeContext
import woowacourse.movie.view.reservation.ReservationActivity
import woowacourse.performClick

class ReservationActivityTest {
    private lateinit var intent: Intent
    private lateinit var scenario: ActivityScenario<ReservationActivity>

    @Before
    fun setup() {
        intent =
            Intent(
                fakeContext,
                ReservationActivity::class.java,
            ).apply {
                putExtra("data", MOVIE)
            }
        ActivityScenario.launch<ReservationActivity>(intent)
    }

    @Test
    fun 영화_티켓_개수_플러스_버튼을_누르면_티켓_개수가_1_증가한다() {
        // given: 영화 개수 초기값은 1이고
        // when: 사용자가 플러스 버튼을 누르면
        onView(withId(R.id.btn_reservation_plus_ticket_count)).performClick()

        // then: 화면의 영화 개수 값은 2가 된다
        onView(withId(R.id.tv_reservation_ticket_count)).checkMatchText("2")
    }

    @Test
    fun 영화_티켓_개수_마이너스_버튼을_누르면_티켓_개수가_1_감소한다() {
        // given: 선택한 영화 개수가 2이고
        onView(withId(R.id.btn_reservation_plus_ticket_count)).performClick()

        // when: 사용자가 마이너스 버튼을 누르면
        onView(withId(R.id.btn_reservation_minus_ticket_count)).performClick()

        // then: 화면의 영화 개수 값은 1이 된다
        onView(withId(R.id.tv_reservation_ticket_count)).checkMatchText("1")
    }

    @Test
    fun `화면을_회전해도_티켓_개수가_유지된다`() {
        // given: 초기 티켓 개수는 1이고
        val intent =
            Intent(
                fakeContext,
                ReservationActivity::class.java,
            ).putExtra("data", MOVIE)
        scenario = ActivityScenario.launch(intent)

        // when: 티켓 개수를 1증가 시키고 가로로 회전 시켰을 때
        onView(withId(R.id.btn_reservation_plus_ticket_count)).performClick()

        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        // then: 티켓 개수 2가 유지 된다
        onView(withId(R.id.tv_reservation_ticket_count)).checkMatchText("2")
    }
}

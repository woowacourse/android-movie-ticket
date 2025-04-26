package woowacourse.movie.reservationComplete

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.MOVIE_TICKET_B1_C3
import woowacourse.movie.fakeContext
import woowacourse.movie.view.reservationComplete.ReservationCompleteActivity

class ReservationCompleteActivityTest {
    private lateinit var intent: Intent

    @Before
    fun setUp() {
        intent =
            Intent(
                fakeContext,
                ReservationCompleteActivity::class.java,
            ).apply {
                putExtra("movieTicket", MOVIE_TICKET_B1_C3)
            }
        ActivityScenario.launch<ReservationCompleteActivity>(intent)
    }

    @Test
    fun 전달_받은_영화_이름_예약_날짜_인원_가격을_출력한다() {
        onView(withText("라라랜드")).check(matches(isDisplayed()))
        onView(withText("2025.04.01 0:00")).check(matches(isDisplayed()))
        onView(withText("일반 2명 | B1, C3")).check(matches(isDisplayed()))
        onView(withText("25,000원 (현장 결제)")).check(matches(isDisplayed()))
    }
}

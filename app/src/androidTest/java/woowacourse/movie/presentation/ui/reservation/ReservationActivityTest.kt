package woowacourse.movie.presentation.ui.reservation

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.domain.repository.DummyReservation
import woowacourse.movie.domain.repository.ReservationRepository
import woowacourse.movie.presentation.utils.currency
import woowacourse.movie.presentation.utils.getDummyScreen

@RunWith(AndroidJUnit4::class)
class ReservationActivityTest {
    private val repository: ReservationRepository by lazy { DummyReservation }
    private var reservationId: Int
    private val screen = getDummyScreen()
    private val count = 1

    @get:Rule
    val activityRule: ActivityScenarioRule<ReservationActivity> =
        ActivityScenarioRule<ReservationActivity>(
            Intent(
                ApplicationProvider.getApplicationContext(),
                ReservationActivity::class.java,
            ).apply {
                reservationId = repository.saveReservation(screen, count).getOrThrow()
                putExtra("reservationId", reservationId)
            },
        )

    @Test
    fun `예약한_영화의_제목을_표시한다`() {
        onView(withId(R.id.tv_reservation_title)).check(matches(withText(screen.movie.title)))
    }

    @Test
    fun `예약한_영화의_날짜를_표시한다`() {
        onView(withId(R.id.tv_reservation_date)).check(matches(withText(screen.date)))
    }

    @Test
    fun `상영_영화_예매_수를_표시한다`() {
        onView(withId(R.id.tv_reservation_count)).check(matches(withText("일반 ${count}명")))
    }

    @Test
    fun `상영_영화_예매_가격을_표시한다`() {
        val reservation = repository.findByReservationId(reservationId)
        val context = ApplicationProvider.getApplicationContext<Context>()

        onView(withId(R.id.tv_reservation_amount)).check(
            matches(withText(reservation.getOrThrow().currency(context))),
        )
    }
}

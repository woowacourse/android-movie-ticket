package woowacourse.movie.view.reservation.result

import android.content.Intent
import android.os.Bundle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.view.fixture.fakeContext
import java.time.LocalDateTime

class ReservationResultActivityTest {
    @Before
    fun setUp() {
        val reservationInfo =
            ReservationInfo(
                title = "해리 포터와 마법사의 돌",
                reservationDateTime = LocalDateTime.of(2025, 4, 15, 11, 0),
                reservationNumber = 2,
            )
        val bundle = Bundle().apply { putSerializable("reservation_info", reservationInfo) }
        val intent =
            Intent(fakeContext, ReservationResultActivity::class.java).putExtras(bundle)

        ActivityScenario.launch<ReservationResultActivity>(intent)
    }

    @Test
    fun `예매_취소_가능_시간을_보여준다`() {
        onView(withId(R.id.tv_cancel_description))
            .check(matches(withText("영화 상영 시작 시간 15분 전까지\n취소가 가능합니다.")))
    }

    @Test
    fun `예매한_영화의_제목을_보여준다`() {
        onView(withId(R.id.tv_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `예매한_영화의_상영일을_보여준다`() {
        onView(withId(R.id.tv_movie_date))
            .check(matches(withText("2025.4.15 11:00")))
    }

    @Test
    fun `예매한_영화의_예매_인원_수를_보여준다`() {
        onView(withId(R.id.tv_reservation_number_info))
            .check(matches(withText("일반 2명")))
    }

    @Test
    fun `예매한_영화의_인원수에_맞는_총_티켓_가격을_보여준다`() {
        onView(withId(R.id.tv_reservation_total_price))
            .check(matches(withText("26,000원 (현장 결제)")))
    }
}

package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.completed.ReservationCompletedActivity
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Quantity
import woowacourse.movie.model.Reservation
import woowacourse.movie.model.pricing.UniformPricingSystem
import woowacourse.movie.model.screening.Schedule
import woowacourse.movie.model.screening.Screening

@RunWith(AndroidJUnit4::class)
class ReservationCompletedActivityTest {
    private val movie =
        Movie(R.drawable.poster, "영화 제목", "영화 설명", "2024.3.1", 152)
    private val screening = Screening(movie, Schedule("2024.3.1"), Quantity(3))
    private val reservation = Reservation(screening, UniformPricingSystem(13000))
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            ReservationCompletedActivity::class.java,
        ).also { it.putExtra("reservation", reservation) }

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule<ReservationCompletedActivity>(intent)

    @Test
    fun `예약_완료_화면에_안내_메세지가_표시된다`() {
        Espresso.onView(ViewMatchers.withId(R.id.message))
            .check(ViewAssertions.matches(ViewMatchers.withText("영화 상영 시작 시간 15분 전까지\n취소가 가능합니다")))
    }

    @Test
    fun `예매_티켓_수량에_맞는_명_수가_표시된다`() {
        Espresso.onView(ViewMatchers.withId(R.id.quantity))
            .check(ViewAssertions.matches(ViewMatchers.withText("일반 3명")))
    }

    @Test
    fun `예매_티켓_수량에_맞는_가격이_표시된다`() {
        Espresso.onView(ViewMatchers.withId(R.id.price))
            .check(ViewAssertions.matches(ViewMatchers.withText("39,000원 (현장 결제)")))
    }
}

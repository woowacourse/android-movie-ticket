package woowacourse.movie

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.jupiter.api.Test
import woowacourse.movie.model.ReservationInfo
import woowacourse.movie.view.Extras
import woowacourse.movie.view.reservation.complete.ReservationCompleteActivity
import java.time.LocalDate

class ReservationCompleteActivityTest {
    private lateinit var scenario: ActivityScenario<ReservationCompleteActivity>
    private val fakeReservationInfo =
        ReservationInfo(
            "라라랜드",
            LocalDate.of(2025, 4, 1),
            "18:00",
            listOf("A1", "D2"),
            25000,
        )
    private val fakeContext: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        scenario =
            ActivityScenario.launch(
                Intent(fakeContext, ReservationCompleteActivity::class.java).putExtra(
                    Extras.ReservationInfoData.RESERVATION_KEY,
                    fakeReservationInfo,
                ),
            )
    }

    @Test
    fun 영화_예매_정보가_화면에_표시된다() {
        onView(withId(R.id.tv_reservation_complete_title))
            .check(matches(isDisplayed()))
//        onView(withId(R.id.tv_reservation_complete_timestamp))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.tv_reservation_complete_count_seats))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.tv_reservation_complete_information))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.tv_reservation_complete_ticket_price))
//            .check(matches(isDisplayed()))
    }
}

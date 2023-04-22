package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.activity.seatselect.SeatSelectActivity
import woowacourse.movie.activity.ticketresult.TicketResultActivity
import woowacourse.movie.model.PriceModel
import woowacourse.movie.model.SeatModel
import woowacourse.movie.model.TicketModel
import java.time.LocalDateTime

class TicketResultActivityTest {
    private val ticket = TicketModel(
        "해리포터와 마법사의 돌 1",
        LocalDateTime.of(2023, 3, 8, 12, 0),
        2,
        listOf(SeatModel(0, 0), SeatModel(3, 2)),
        PriceModel("25,000")
    )

    private val intent =
        Intent(ApplicationProvider.getApplicationContext(), TicketResultActivity::class.java).apply {
            putExtra(TicketResultActivity.INFO_KEY, ticket)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<SeatSelectActivity>(intent)

    @Test
    fun 영화_제목을_띄운다() {
        onView(withId(R.id.text_title)).check(matches(withText("해리포터와 마법사의 돌 1")))
    }

    @Test
    fun 상영_시간을_띄운다() {
        onView(withId(R.id.text_playing_date)).check(matches(withText("2023.3.8 12:00")))
    }

    @Test
    fun 인원_수와_좌석_위치를_띄운다() {
        onView(withId(R.id.text_person_count_seats)).check(matches(withText("일반 2명 | A1, D3")))
    }

    @Test
    fun 가격을_띄운다() {
        onView(withId(R.id.text_price_payment)).check(matches(withText("25,000원 (현장 결제)")))
    }
}

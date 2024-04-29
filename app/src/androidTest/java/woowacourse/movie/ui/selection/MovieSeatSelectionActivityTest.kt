package woowacourse.movie.ui.selection

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.not
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.model.data.UserTicketsImpl
import woowacourse.movie.model.movie.ReservationDetail
import woowacourse.movie.model.movie.UserTicket
import woowacourse.movie.ui.reservation.MovieReservationKey
import java.time.LocalDateTime

class MovieSeatSelectionActivityTest {
    private val userTicket: UserTicket = UserTicketsImpl.find(0L)

    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieSeatSelectionActivity::class.java,
        ).run {
            putExtra(MovieReservationKey.TICKET_ID, 0L)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieSeatSelectionActivity>(intent)

    @Test
    fun `화면이_띄워지면_영화_제목이_보인다`() {
        onView(withId(R.id.movie_title_text))
            .check(matches(isDisplayed()))
            .check(matches(withText(userTicket.title)))
    }

    @Test
    fun `화면이_띄워질_때_초기_좌석_선택_가격의_합은_0원이다`() {
        onView(withId(R.id.total_seat_amount_text))
            .check(matches(isDisplayed()))
            .check(matches(withText("0원")))
    }

    @Test
    fun `A1_좌석을_선택한_경우_가격은_10000원이다`() {
        onView(withText("A1"))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.total_seat_amount_text))
            .check(matches(isDisplayed()))
            .check(matches(withText("10,000원")))
    }

    @Test
    fun `예매인원이_2명인_경우_2개_미만의_좌석을_선택하면_확인_버튼이_활성화_되지_않는다`() {
        onView(withText("A1"))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.confirm_button))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun `예매인원이_2명인_경우_2개의_좌석을_선택하면_확인_버튼이_활성화_된다`() {
        onView(withText("A1"))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withText("A2"))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.confirm_button))
            .check(matches(isEnabled()))
    }

    companion object {
        @JvmStatic
        @BeforeClass
        fun setUp() {
            UserTicketsImpl.save(
                UserTicket(
                    "해리포터와 마법사의 돌0",
                    LocalDateTime.of(2024, 3, 28, 10, 0),
                    ReservationDetail(2),
                    0L,
                ),
            )
        }
    }
}

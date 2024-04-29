package woowacourse.movie.view.finished

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.TestFixture.FIRST_MOVIE_ITEM_POSITION
import woowacourse.movie.TestFixture.makeMockSeats
import woowacourse.movie.TestFixture.makeMockTicket
import woowacourse.movie.TestFixture.movies
import woowacourse.movie.view.reservation.ReservationDetailActivity

class ReservationFinishedActivityTest {
    @get:Rule
    var activityRule =
        ActivityScenarioRule<ReservationFinishedActivity>(
            Intent(
                ApplicationProvider.getApplicationContext(),
                ReservationFinishedActivity::class.java,
            ).apply {
                putExtra("movieId", FIRST_MOVIE_ITEM_POSITION)
                putExtra("ticket", makeMockTicket())
                putExtra("seats", makeMockSeats())
            },
        )

    @Test
    fun `예매한_영화의_제목을_보여준다`() {
        onView(withId(R.id.text_view_reservation_finished_title)).check(matches(withText(movies[FIRST_MOVIE_ITEM_POSITION].title)))
    }

    @Test
    fun `예매한_영화의_상영일을_보여준다`() {
        onView(
            withId(R.id.text_view_reservation_finished_screening_date),
        ).check(matches(withText(makeMockTicket().screeningDateTime.date)))
    }

    @Test
    fun `예매한_영화의_관람인원을_보여준다`() {
        onView(withId(R.id.text_view_reservation_finished_number_of_tickets)).check(
            matches(
                withText(
                    "2",
                ),
            ),
        )
    }

    @Test
    fun `예매한_영화의_총_결제금액을_보여준다`() {
        onView(withId(R.id.text_view_reservation_finished_ticket_price)).check(
            matches(
                withText(
                    "25,000",
                ),
            ),
        )
    }

    @Test
    fun `영화_예매_완료_화면은_영화_상세_화면의_예매_완료_버튼을_누르면_보여진다`() {
        ActivityScenario.launch(ReservationDetailActivity::class.java)
        onView(withId(R.id.button_reservation_detail_finished)).perform(ViewActions.click())
        onView(withId(R.id.constraint_layout_seat_selection)).check(matches(ViewMatchers.isDisplayed()))
    }
}

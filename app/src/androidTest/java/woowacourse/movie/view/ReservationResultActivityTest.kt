package woowacourse.movie.view

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
import woowacourse.movie.data.MovieDao
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket
import woowacourse.movie.presentation.reservation.result.ReservationResultActivity
import woowacourse.movie.presentation.reservation.seat.ReservationSeatActivity.Companion.SEATS
import woowacourse.movie.presentation.screen.detail.MovieDetailActivity.Companion.TICKET
import woowacourse.movie.presentation.screen.movie.ScreeningMovieActivity.Companion.MOVIE_ID

@RunWith(AndroidJUnit4::class)
class ReservationResultActivityTest {
    private val movieId = 0
    private val movie = MovieDao().find(movieId)
    private val ticket = Ticket(1)

    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            ReservationResultActivity::class.java,
        ).putExtra(MOVIE_ID, movieId)
            .putExtra(TICKET, ticket)
            .putExtra(SEATS, Seats())

    @get:Rule
    val activityRule = ActivityScenarioRule<ReservationResultActivity>(intent)

    @Test
    fun 액티비티가_시작하면_제목이_보인다() {
        onView(withId(R.id.result_title_textview))
            .check(matches(withText(movie.title)))
    }

    @Test
    fun 액티비티가_시작하면_상영날짜가_보인다() {
        onView(withId(R.id.result_screen_date_textview))
            .check(matches(withText(ticket.screeningInfo.first.toString())))
    }

    @Test
    fun 액티비티가_시작하면_상영시간이_보인다() {
        onView(withId(R.id.result_screen_time_textview))
            .check(matches(withText(ticket.screeningInfo.second.toString())))
    }

    @Test
    fun 액티비티가_시작하면_count가_보인다() {
        onView(withId(R.id.result_count_textview))
            .check(matches(withText(ticket.count().toString())))
    }
}

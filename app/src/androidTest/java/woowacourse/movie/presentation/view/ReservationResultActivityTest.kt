package woowacourse.movie.presentation.view

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.data.repository.MovieTicketRepositoryImpl
import woowacourse.movie.presentation.ui.reservation.ReservationResultActivity

class ReservationResultActivityTest {
    private val testContext = ApplicationProvider.getApplicationContext<Context>()
    private val intent: Intent = reservationResultActivityIntent(testContext, 1)

    @get:Rule
    val activityRule = ActivityScenarioRule<ReservationResultActivity>(intent)

    private fun reservationResultActivityIntent(
        context: Context,
        movieTicketId: Int,
    ): Intent =
        Intent(context, ReservationResultActivity::class.java).apply {
            putExtra(EXTRA_MOVIE_TICKET_ID, movieTicketId)
        }

    @Before
    fun setup() {
        // given 테스트용 예매 정보 생성
        MovieTicketRepositoryImpl.createMovieTicket(TITLE, SCREENING_LOCAL_TIME)
    }

    @Test
    fun `영화_상세_화면이_나타난다`() {
        // then
        onView(withId(R.id.reservation_result_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun `영화_상세_화면에서_전달_받은_예매_정보를_화면에_나타낸다`() {
        // then
        onView(withId(R.id.title)).check(matches(withText(TITLE)))
        onView(withId(R.id.screeningDate)).check(matches(withText(SCREENING_DATE)))
        onView(
            withId(R.id.reservationCount),
        ).check(
            matches(
                withText(
                    testContext.getString(
                        R.string.reservation_count_format,
                        1,
                    ),
                ),
            ),
        )
        onView(
            withId(R.id.totalPrice),
        ).check(
            matches(
                withText(
                    testContext.getString(
                        R.string.total_price_format,
                        13000,
                    ),
                ),
            ),
        )
    }
}

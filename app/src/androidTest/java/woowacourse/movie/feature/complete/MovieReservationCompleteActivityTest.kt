package woowacourse.movie.feature.complete

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.feature.FIRST_MOVIE_CONTENT_ID
import woowacourse.movie.feature.dateMessage
import woowacourse.movie.feature.equalText
import woowacourse.movie.feature.firstMovieContent
import woowacourse.movie.feature.reservationAmountMessage
import woowacourse.movie.feature.reservationCountMessage
import woowacourse.movie.feature.view

@RunWith(AndroidJUnit4::class)
class MovieReservationCompleteActivityTest {
    private val reservationCount = 1
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieReservationCompleteActivity::class.java,
        ).run {
            putExtra("movie_content_id", FIRST_MOVIE_CONTENT_ID)
            putExtra("reservation_count_key", reservationCount)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieReservationCompleteActivity>(intent)

    @Test
    fun `화면이_띄워지면_영화_제목이_보인다`() {
        view(R.id.title_text)
            .equalText(firstMovieContent.title)
    }

    @Test
    fun `화면이_띄워지면_상영일이_보인다`() {
        view(R.id.screening_date_text)
            .equalText(firstMovieContent.dateMessage())
    }

    @Test
    fun `화면이_띄워지면_예매_인원이_보인다`() {
        view(R.id.reservation_count_text)
            .equalText(reservationCount.reservationCountMessage())
    }

    @Test
    fun `화면이_띄워지면_예매_금액이_보인다`() {
        view(R.id.reservation_amount_text)
            .equalText(reservationCount.reservationAmountMessage())
    }
}

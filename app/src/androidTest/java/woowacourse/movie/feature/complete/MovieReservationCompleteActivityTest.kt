package woowacourse.movie.feature.complete

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.feature.FIRST_MOVIE_ID
import woowacourse.movie.feature.equalText
import woowacourse.movie.feature.firstMovie
import woowacourse.movie.feature.reservationAmountMessage
import woowacourse.movie.feature.reservationCountMessage
import woowacourse.movie.feature.view
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RunWith(AndroidJUnit4::class)
class MovieReservationCompleteActivityTest {
    private val reservationCount = 1
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieReservationCompleteActivity::class.java,
        ).apply {
            putExtra("movie_id", FIRST_MOVIE_ID)
            putExtra("reservation_count_key", reservationCount)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieReservationCompleteActivity>(intent)

    @Test
    fun `화면이_띄워지면_영화_제목이_보인다`() {
        view(R.id.title_text)
            .equalText(firstMovie.title)
    }

    @Test
    fun `화면이_띄워지면_상영_시간이_보인다`() {
        // TODO("intent로 상영 시간 받아오기")
//        val screeningTime = LocalDateTime.of(2024, 3, 1, 17, 0)
//        view(R.id.screening_date_text)
//            .equalText(screeningTimeMessage(screeningTime))
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

    private fun screeningTimeMessage(screeningDate: LocalDateTime): String {
        return screeningDate.format(DateTimeFormatter.ofPattern("yyyy.M.d HH:mm"))
    }
}

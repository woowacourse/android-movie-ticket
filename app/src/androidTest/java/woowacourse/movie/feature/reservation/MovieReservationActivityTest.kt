package woowacourse.movie.feature.reservation

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.feature.FIRST_MOVIE_CONTENT_ID
import woowacourse.movie.feature.click
import woowacourse.movie.feature.equalText
import woowacourse.movie.feature.firstMovieContent
import woowacourse.movie.feature.runningTimeMessage
import woowacourse.movie.feature.screeningDateMessage
import woowacourse.movie.feature.scroll
import woowacourse.movie.feature.view

@RunWith(AndroidJUnit4::class)
class MovieReservationActivityTest {
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieReservationActivity::class.java,
        ).run {
            putExtra("movie_content_id", FIRST_MOVIE_CONTENT_ID)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieReservationActivity>(intent)

    @Test
    fun `화면이_띄워지면_영화_제목이_보인다`() {
        view(R.id.title_text)
            .equalText(firstMovieContent.title)
    }

    @Test
    fun `화면이_띄워지면_상영일이_보인다`() {
        view(R.id.screening_date_text)
            .equalText(firstMovieContent.screeningDateMessage())
    }

    @Test
    fun `화면이_띄워지면_러닝타임이_보인다`() {
        view(R.id.running_time_text)
            .equalText(firstMovieContent.runningTimeMessage())
    }

    @Test
    fun `스크롤_하면_시놉시스가_보인다`() {
        view(R.id.synopsis_text)
            .scroll()
            .equalText(firstMovieContent.synopsis)
    }

    @Test
    fun `초기_예매_인원은_1이고_증가_버튼을_누르면_예매_인원_수가_증가한다`() {
        // given
        view(R.id.reservation_count_text)
            .equalText("1")

        // when
        view(R.id.plus_button)
            .click()

        // then
        view(R.id.reservation_count_text)
            .equalText("2")
    }

    @Test
    fun `예매_인원이_최대인_경우_증가_버튼을_누르면_예매_인원_수가_증가하지_않는다`() {
        // given
        repeat(50) {
            view(R.id.plus_button)
                .click()
        }

        // when
        view(R.id.plus_button)
            .click()

        // then
        view(R.id.reservation_count_text)
            .equalText("50")
    }

    @Test
    fun `감소_버튼을_누르면_예매_인원_수가_감소한다`() {
        // given (reservation count = 3)
        view(R.id.plus_button)
            .click()
            .click()

        // when
        view(R.id.minus_button)
            .click()

        // then
        view(R.id.reservation_count_text)
            .equalText("2")
    }

    @Test
    fun `예매_인원이_최소인_경우_감소_버튼을_누르면_예매_인원_수가_감소하지_않는다`() {
        // given
        view(R.id.reservation_count_text)
            .equalText("1")

        // when
        view(R.id.minus_button)
            .click()

        // then
        view(R.id.reservation_count_text)
            .equalText("1")
    }
}

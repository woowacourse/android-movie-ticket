package woowacourse.movie.feature.reservation

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.feature.FIRST_MOVIE_ID
import woowacourse.movie.feature.click
import woowacourse.movie.feature.equalText
import woowacourse.movie.feature.equalTextSpinnerItem
import woowacourse.movie.feature.firstMovie
import woowacourse.movie.feature.scroll
import woowacourse.movie.feature.spinnerItemByText
import woowacourse.movie.feature.view
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RunWith(AndroidJUnit4::class)
class MovieReservationActivityTest {
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieReservationActivity::class.java,
        ).apply {
            putExtra("movie_id_key", FIRST_MOVIE_ID)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieReservationActivity>(intent)

    @Test
    fun `화면이_띄워지면_영화_제목이_보인다`() {
        view(R.id.title_text)
            .equalText(firstMovie.title)
    }

    @Test
    fun `화면이_띄워지면_상영기간이_보인다`() {
        val screeningDateRangeMessage =
            "상영일: %s ~ %s".format(
                firstMovie.startScreeningDate.message(),
                firstMovie.endScreeningDate.message(),
            )
        view(R.id.screening_date_text)
            .equalText(screeningDateRangeMessage)
    }

    @Test
    fun `화면이_띄워지면_러닝타임이_보인다`() {
        view(R.id.running_time_text)
            .equalText("러닝타임: %d분".format(firstMovie.runningTime))
    }

    @Test
    fun `스크롤_하면_시놉시스가_보인다`() {
        view(R.id.synopsis_text)
            .scroll()
            .equalText(firstMovie.synopsis)
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
        repeat(20) {
            view(R.id.plus_button)
                .click()
        }

        // when
        view(R.id.plus_button)
            .click()

        // then
        view(R.id.reservation_count_text)
            .equalText("20")
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

    @Test
    fun `예매_인원이_2일_때_화면을_회전시키면_그대로_유지된다`() {
        // given
        view(R.id.plus_button)
            .click()

        // when
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        // then
        view(R.id.reservation_count_text)
            .equalText("2")
    }

    @Test
    fun `2024년_3월_15일_상영일을_선택하면_스피너에_해당하는_상영일이_보인다`() {
        // given
        view(R.id.screening_date_spinner)
            .click()

        // when
        spinnerItemByText("2024-3-15")
            .click()

        // then
        view(R.id.screening_date_spinner)
            .equalTextSpinnerItem("2024-3-15")
    }

    @Test
    fun `16시_상영_시간을_선택하면_스피너에_해당하는_상영_시간이_보인다`() {
        // given
        view(R.id.screening_time_spinner)
            .click()

        // when
        spinnerItemByText("16:00")
            .click()

        // then
        view(R.id.screening_time_spinner)
            .equalTextSpinnerItem("16:00")
    }

    @Test
    fun `상영일이_주말이면_상영_시간이_9시부터_시작한다`() {
        // given
        view(R.id.screening_date_spinner)
            .click()

        // when
        spinnerItemByText("2024-3-9")
            .click()

        // then
        view(R.id.screening_time_spinner)
            .equalTextSpinnerItem("09:00")
    }

    @Test
    fun `상영일이_평일이면_상영_시간이_10시부터_시작한다`() {
        // given
        view(R.id.screening_date_spinner)
            .click()

        // when
        spinnerItemByText("2024-3-8")
            .click()

        // then
        view(R.id.screening_time_spinner)
            .equalTextSpinnerItem("10:00")
    }

    @Test
    fun `상영일을_2024년_3월_2일로_선택하고_화면을_회전시키면_그대로_유지된다`() {
        // given
        view(R.id.screening_date_spinner)
            .click()
        spinnerItemByText("2024-3-2")
            .click()

        // when
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        // then
        view(R.id.screening_date_spinner)
            .equalTextSpinnerItem("2024-3-2")
    }

    @Test
    fun `상영_시간을_14시로_선택하고_화면을_회전시키면_그대로_유지된다`() {
        // given
        view(R.id.screening_time_spinner)
            .click()
        spinnerItemByText("14:00")
            .click()

        // when
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        // then
        view(R.id.screening_time_spinner)
            .equalTextSpinnerItem("14:00")
    }

    private fun LocalDate.message() = format(DateTimeFormatter.ofPattern("yyyy.M.d"))
}

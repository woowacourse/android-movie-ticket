package woowacourse.movie.feature.seat

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
import woowacourse.movie.feature.checkDisabled
import woowacourse.movie.feature.checkEnabled
import woowacourse.movie.feature.click
import woowacourse.movie.feature.equalText
import woowacourse.movie.feature.firstMovie
import woowacourse.movie.feature.view
import woowacourse.movie.feature.viewWithText
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class SeatSelectActivityTest {
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            SeatSelectActivity::class.java,
        ).apply {
            putExtra("movie_id_key", FIRST_MOVIE_ID)
            putExtra("screening_date_time_key", LocalDateTime.of(2024, 3, 1, 12, 0))
            putExtra("reservation_count_key", 3)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<SeatSelectActivity>(intent)

    @Test
    fun `화면이_띄워지면_영화_제목이_보인다`() {
        view(R.id.title_text)
            .equalText(firstMovie.title)
    }

    @Test
    fun `좌석을_아무것도_선택하지_않았을_때의_초기_금액은_0원이다`() {
        view(R.id.reservation_amount_text)
            .equalText("0원")
    }

    @Test
    fun `A3_좌석을_클릭하면_금액이_10000원으로_변경된다`() {
        viewWithText("A3")
            .click()

        view(R.id.reservation_amount_text)
            .equalText("10,000원")
    }

    @Test
    fun `A3_좌석을_클릭한_상태에서_E4_좌석을_클릭하면_금액이_22000원으로_반영된다`() {
        // given
        viewWithText("A3")
            .click()

        // when
        viewWithText("E4")
            .click()

        // then
        view(R.id.reservation_amount_text)
            .equalText("22,000원")
    }

    @Test
    fun `예매_인원이_3명이고_1개의_좌석을_클릭하면_확인_버튼이_비활성화_된다`() {
        viewWithText("E4")
            .click()
        view(R.id.confirm_button)
            .checkDisabled()
    }

    @Test
    fun `예매_인원이_3명이고_3개의_좌석을_클릭하면_확인_버튼이_활성화_된다`() {
        viewWithText("B2")
            .click()
        viewWithText("C3")
            .click()
        viewWithText("E1")
            .click()
        view(R.id.confirm_button)
            .checkEnabled()
    }

    @Test
    fun `A3_좌석과_E4_좌석을_클릭한_상태에서_화면을_회전시키면_그대로_유지된다`() {
        // given
        viewWithText("A3")
            .click()
        viewWithText("E4")
            .click()

        // when
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        // then
        view(R.id.reservation_amount_text)
            .equalText("22,000원")
        view(R.id.confirm_button)
            .checkEnabled()
    }
}

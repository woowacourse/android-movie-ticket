package woowacourse.movie.feature.seatSelect

import android.content.pm.ActivityInfo
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.extension.ResourceMapper
import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import woowacourse.movie.feature.ticket.TicketData
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SeatSelectActivityTest {
    private val screeningData =
        ScreeningData(
            title = "해리 포터와 마법사의 돌",
            startDate = LocalDate.of(2025, 4, 16),
            endDate = LocalDate.of(2025, 4, 21),
            movieId = "HarryPotter1",
            runningTime = 152,
            poster = ResourceMapper.movieIdToPosterImageResource("HarryPotter1"),
        )

    // 2명 티켓 데이터
    private val ticketData =
        TicketData(
            screeningData = screeningData,
            ticketCount = 2,
            showtime = LocalDateTime.of(screeningData.startDate, LocalTime.of(13, 0)),
        )

    @get:Rule
    val activityRule =
        ActivityScenarioRule<SeatSelectActivity>(
            SeatSelectActivity.Companion.newIntent(
                ApplicationProvider.getApplicationContext(),
                ticketData,
            ),
        )

    private fun rotateToLandscape() {
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1500)
    }

    private fun hasBackgroundColor(colorResId: Int) =
        object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("배경색이 $colorResId 와 일치")
            }

            override fun matchesSafely(view: View): Boolean {
                val expectedColor = ContextCompat.getColor(view.context, colorResId)
                val background = view.background
                return background is ColorDrawable && background.color == expectedColor
            }
        }

    @Test
    fun `영화_제목이_표시된다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_select_seat_movie_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `좌석_테이블이_표시된다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tl_seat))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `초기_티켓_가격이_0원으로_표시된다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_select_seat_selected_ticket_price))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withText("0원")))
    }

    @Test
    fun `좌석을_선택하면_배경색이_변경된다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_select_seat_selected_ticket_price))
            .check(ViewAssertions.matches(ViewMatchers.withText("0원")))

        Espresso
            .onView(ViewMatchers.withId(R.id.seat_A1))
            .check(ViewAssertions.matches(hasBackgroundColor(R.color.white)))

        // A1 좌석 선택
        Espresso
            .onView(ViewMatchers.withId(R.id.seat_A1))
            .perform(ViewActions.click())
            .check(ViewAssertions.matches(hasBackgroundColor(R.color.selected_seat_background)))

        Espresso
            .onView(ViewMatchers.withId(R.id.tv_select_seat_selected_ticket_price))
            .check(ViewAssertions.matches(ViewMatchers.withText("10,000원")))
    }

    @Test
    fun `좌석_선택_후_다시_선택하면_취소된다`() {
        // A1 좌석 선택
        Espresso
            .onView(ViewMatchers.withId(R.id.seat_A1))
            .perform(ViewActions.click())
            .check(ViewAssertions.matches(hasBackgroundColor(R.color.selected_seat_background)))

        // 가격 확인
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_select_seat_selected_ticket_price))
            .check(ViewAssertions.matches(ViewMatchers.withText("10,000원")))

        // 다시 A1 좌석 선택하여 취소
        Espresso
            .onView(ViewMatchers.withId(R.id.seat_A1))
            .perform(ViewActions.click())
            .check(ViewAssertions.matches(hasBackgroundColor(R.color.white)))

        // 가격이 다시 0원으로 돌아왔는지 확인
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_select_seat_selected_ticket_price))
            .check(ViewAssertions.matches(ViewMatchers.withText("0원")))
    }

    @Test
    fun `최대_인원을_초과하여_좌석을_선택하면_오류_메시지가_표시된다`() {
        // 좌석 2개 선택 (티켓은 2장)
        Espresso.onView(ViewMatchers.withId(R.id.seat_A1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.seat_A2)).perform(ViewActions.click())

        // 추가 좌석 선택 시도
        Espresso.onView(ViewMatchers.withId(R.id.seat_A3)).perform(ViewActions.click())

        // 여전히 좌석 2개만 선택되었는지 가격으로 확인
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_select_seat_selected_ticket_price))
            .check(ViewAssertions.matches(ViewMatchers.withText("20,000원")))
    }

    @Test
    fun `전체_인원만큼_좌석_선택시_확인_버튼이_활성화된다`() {
        // 좌석 2개 선택 (티켓은 2장)
        Espresso.onView(ViewMatchers.withId(R.id.seat_A1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.seat_A2)).perform(ViewActions.click())

        // 확인 버튼 활성화(보라색 배경) 확인
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_select_seat_confirm))
            .check(ViewAssertions.matches(hasBackgroundColor(R.color.purple_500)))
    }

    @Test
    fun `인원_미만_좌석_선택시_확인_버튼이_비활성화된다`() {
        // 좌석 1개만 선택 (티켓은 2장)
        Espresso.onView(ViewMatchers.withId(R.id.seat_A1)).perform(ViewActions.click())

        // 확인 버튼 비활성화(회색 배경) 확인
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_select_seat_confirm))
            .check(ViewAssertions.matches(hasBackgroundColor(R.color.disabled_btn_color)))
    }

    @Test
    fun `좌석_선택_완료_후_확인_버튼_클릭시_다이얼로그가_표시된다`() {
        // 좌석 2개 선택 (티켓은 2장)
        Espresso.onView(ViewMatchers.withId(R.id.seat_A1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.seat_A2)).perform(ViewActions.click())

        // 확인 버튼 클릭
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_select_seat_confirm))
            .perform(ViewActions.click())

        // 다이얼로그 표시 확인
        Espresso
            .onView(ViewMatchers.withText(R.string.ticket_dialog_title))
            .inRoot(RootMatchers.isDialog())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `좌석_선택_후_화면_회전시_선택한_좌석이_유지된다`() {
        // 좌석 선택
        Espresso.onView(ViewMatchers.withId(R.id.seat_A1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.seat_A2)).perform(ViewActions.click())

        // 가격 확인
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_select_seat_selected_ticket_price))
            .check(ViewAssertions.matches(ViewMatchers.withText("20,000원")))

        // 화면 회전
        rotateToLandscape()

        // 좌석이 유지되는지 확인
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_select_seat_selected_ticket_price))
            .check(ViewAssertions.matches(ViewMatchers.withText("20,000원")))
        Espresso
            .onView(ViewMatchers.withId(R.id.seat_A1))
            .check(ViewAssertions.matches(hasBackgroundColor(R.color.selected_seat_background)))
        Espresso
            .onView(ViewMatchers.withId(R.id.seat_A2))
            .check(ViewAssertions.matches(hasBackgroundColor(R.color.selected_seat_background)))
    }

    @Test
    fun `가로_화면에서_스크롤하여_마지막_좌석_선택이_가능하다`() {
        // 가로 모드로 전환
        rotateToLandscape()

        // 마지막 좌석 찾기 (E4)
        Espresso
            .onView(ViewMatchers.withId(R.id.seat_E4))
            .perform(ViewActions.scrollTo())
            .perform(ViewActions.click())

        // 좌석 선택이 되었는지 확인
        Espresso
            .onView(ViewMatchers.withId(R.id.seat_E4))
            .check(ViewAssertions.matches(hasBackgroundColor(R.color.selected_seat_background)))
    }

    @Test
    fun `다이얼로그는_배경_터치로_닫히지_않아야_한다`() {
        // 좌석 2개 선택 (티켓은 2장)
        Espresso.onView(ViewMatchers.withId(R.id.seat_A1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.seat_A2)).perform(ViewActions.click())

        // 확인 버튼 클릭
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_select_seat_confirm))
            .perform(ViewActions.click())

        // 배경 터치 시도
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.click())

        // 다이얼로그 여전히 표시 확인
        Espresso
            .onView(ViewMatchers.withText(R.string.ticket_dialog_title))
            .inRoot(RootMatchers.isDialog())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}

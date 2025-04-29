package woowacourse.movie

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.view.Extras
import woowacourse.movie.view.reservation.seat.SeatSelectActivity
import java.time.LocalDate

class SeatSelectActivityTest {
    private lateinit var scenario: ActivityScenario<SeatSelectActivity>
    private val fakeTicket =
        MovieTicket(
            "라라랜드",
            LocalDate.of(2025, 4, 1),
            "18:00",
            2,
        )
    private val fakeContext: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        scenario =
            ActivityScenario.launch(
                Intent(fakeContext, SeatSelectActivity::class.java).putExtra(
                    Extras.TicketData.TICKET_KEY,
                    fakeTicket,
                ),
            )
    }

    @Test
    fun 확인_버튼을_누르면_예매_확인_다이얼로그에_타이틀이_표시된다() {
        // given: 좌석 선택 페이지에서
        // when: 선택 완료 버튼을 누르면
        onView(withId(R.id.btn_seat_select_confirm))
            .perform(click())

        // then: 예매 확인 다이얼로그에 타이틀이 표시된다
        onView(withText("예매 확인"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 선택_완료_버튼을_누르면_예매_확인_다이얼로그에_메시지가_표시된다() {
        // given: 좌석 선택 페이지에서
        // when: 선택 완료 버튼을 누르면
        onView(withId(R.id.btn_seat_select_confirm))
            .perform(click())

        // then: 예약 확인 다이얼로그에 메시지가 표시된다
        onView(withText("정말 예매하시겠습니까?"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 선택_완료_버튼을_누르면_예매_확인_다이얼로그에_취소_버튼이_표시된다() {
        // given: 좌석 선택 페이지에서
        // when: 선택 완료 버튼을 누르면
        onView(withId(R.id.btn_seat_select_confirm))
            .perform(click())

        // then: 예약 확인 다이얼로그에 취소 버튼이 표시된다
        onView(withText("취소"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 선택_완료_버튼을_누르면_예매_확인_다이얼로그에_예매완료_버튼이_표시된다() {
        // given: 좌석 선택 페이지에서
        // when: 선택 완료 버튼을 누르면
        onView(withId(R.id.btn_seat_select_confirm))
            .perform(click())

        // then: 예약 확인 다이얼로그에 예매 완료 버튼이 표시된다
        onView(withText("예매 완료"))
            .check(matches(isDisplayed()))
    }
}

package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.model.MovieTicket

class ReservationActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(ReservationActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @Test
    fun 영화_티켓_개수_플러스_버튼을_누르면_티켓_개수가_1_증가한다() {
        // given: 영화 개수 초기값은 1이고
        // when: 사용자가 플러스 버튼을 누르면
        onView(withId(R.id.btn_reservation_plus_ticket_count))
            .perform(click())

        // then: 화면의 영화 개수 값은 2가 된다
        onView(withId(R.id.tv_reservation_ticket_count))
            .check(matches(withText("2")))
    }

    @Test
    fun 영화_티켓_개수_마이너스_버튼을_누르면_티켓_개수가_1_감소한다() {
        // given: 선택한 영화 개수가 2이고
        onView(withId(R.id.btn_reservation_plus_ticket_count))
            .perform(click())

        // when: 사용자가 마이너스 버튼을 누르면
        onView(withId(R.id.btn_reservation_minus_ticket_count))
            .perform(click())

        // then: 화면의 영화 개수 값은 1이 된다
        onView(withId(R.id.tv_reservation_ticket_count))
            .check(matches(withText("1")))
    }

    @Test
    fun 선택_완료_버튼을_누르면_예매_확인_다이얼로그에_타이틀이_표시된다() {
        // given: 영화 예매 페이지에서
        // when: 선택 완료 버튼을 누르면
        onView(withId(R.id.btn_reservation_select_complete))
            .perform(click())

        // then: 예매 확인 다이얼로그에 타이틀이 표시된다
        onView(withText("예매 확인"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 선택_완료_버튼을_누르면_예매_확인_다이얼로그에_메시지가_표시된다() {
        // given: 영화 예매 페이지에서
        // when: 선택 완료 버튼을 누르면
        onView(withId(R.id.btn_reservation_select_complete))
            .perform(click())

        // then: 예약 확인 다이얼로그에 메시지가 표시된다
        onView(withText("정말 예매하시겠습니까?"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 선택_완료_버튼을_누르면_예매_확인_다이얼로그에_취소_버튼이_표시된다() {
        // given: 영화 예매 페이지에서
        // when: 선택 완료 버튼을 누르면
        onView(withId(R.id.btn_reservation_select_complete))
            .perform(click())

        // then: 예약 확인 다이얼로그에 취소 버튼이 표시된다
        onView(withText("취소"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 선택_완료_버튼을_누르면_예매_확인_다이얼로그에_예매완료_버튼이_표시된다() {
        // given: 영화 예매 페이지에서
        // when: 선택 완료 버튼을 누르면
        onView(withId(R.id.btn_reservation_select_complete))
            .perform(click())

        // then: 예약 확인 다이얼로그에 예매 완료 버튼이 표시된다
        onView(withText("예매 완료"))
            .check(matches(isDisplayed()))
    }

    // 테스트 값 현재 시간 기준으로 하드 코딩 해야 함
    @Test
    fun 다이얼로그_예매_완료_버튼을_누르면_영화_예매_완료_화면으로_티켓_정보를_넘긴다() {
        // given: 다이얼로그 화면에서
        onView(withId(R.id.btn_reservation_select_complete))
            .perform(click())

        // when: 예매 완료 버튼을 누르면
        onView(withText("예매 완료"))
            .perform(click())

        // then: 영화 예매 완료 화면으로 티켓 정보를 넘긴다
        intended(
            hasExtra(
                "movieTicket",
                MovieTicket(
                    "라라랜드",
                    "2025.04.17 12:00",
                    1,
                ),
            ),
        )
    }

    @After
    fun finish() {
        Intents.release()
    }
}

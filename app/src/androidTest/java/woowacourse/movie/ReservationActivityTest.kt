package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class ReservationActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(ReservationActivity::class.java)

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
}

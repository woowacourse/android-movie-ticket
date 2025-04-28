package woowacourse.movie.uiTest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.R

class SeatingActivityTest {
    @Test
    @DisplayName("'완료' 버튼을 누르면 다이얼로그의 '예매 확인'이 뜬다")
    fun dialogPopUpTitleWhenReservationButtonIsClicked() {
        onView(withText("A1"))
            .perform(click())
        onView(withId(R.id.seating_confirm))
            .perform(click())
        onView(withText("예매 확인"))
            .check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("'선택 완료' 버튼을 누르면 다이얼로그의 '정말 예매하시겠습니까?'가 뜬다")
    fun dialogPopUpDetailWhenReservationButtonIsClicked() {
        onView(withText("A1"))
            .perform(click())
        onView(withId(R.id.detail_reservation_button))
            .perform(click())
        onView(withText("정말 예매하시겠습니까?"))
            .check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("'선택 완료' 버튼을 누르면 다이얼로그에 취소 버튼이 뜬다")
    fun dialogPopUpCancelButtonWhenReservationButtonIsClicked() {
        onView(withText("A1"))
            .perform(click())
        onView(withId(R.id.detail_reservation_button))
            .perform(click())
        onView(withText("취소"))
            .check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("'선택 완료' 버튼을 누르면 다이얼로그에 확인 버튼이 뜬다")
    fun dialogPopUpConfirmButtonWhenReservationButtonIsClicked() {
        onView(withText("A1"))
            .perform(click())
        onView(withId(R.id.detail_reservation_button))
            .perform(click())
        onView(withText("예매 완료"))
            .check(matches(isDisplayed()))
    }

}
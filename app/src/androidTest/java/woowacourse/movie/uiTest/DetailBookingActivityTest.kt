package woowacourse.movie.uiTest

import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.R
import woowacourse.movie.detailbooking.DetailBookingActivity
import woowacourse.movie.domain.Date
import woowacourse.movie.domain.Movie
import java.time.LocalDate

class DetailBookingActivityTest {
    private lateinit var scenario: ActivityScenario<DetailBookingActivity>

    @Before
    fun setUp() {
        val movie =
            Movie(
                R.drawable.harry_potter_and_the_philosopher_stone,
                "해리 포터와 마법사의 돌",
                Date(LocalDate.of(3025, 4, 1), LocalDate.of(3025, 4, 25)),
                152,
            )
        val intent = DetailBookingActivity.newIntent(ApplicationProvider.getApplicationContext(), movie)

        scenario = ActivityScenario.launch(intent)
    }

    @Test
    @DisplayName("영화 포스터는 보여야 한다")
    fun moviePosterIsDisplayed() {
        onView(withId(R.id.scrollView))
            .perform(swipeUp())
        onView(withId(R.id.detail_movie_image))
            .check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("영화 제목은 '해리 포터와 마법사의 돌'이다")
    fun movieTitleIsHarryPotter() {
        onView(withId(R.id.scrollView))
            .perform(swipeUp())
        onView(withId(R.id.detail_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    @DisplayName("영화 상영일은 '상영일: 3025.4.1 ~ 3025.4.25'이다")
    fun movieDateIsApril1to25() {
        onView(withId(R.id.scrollView))
            .perform(swipeUp())
        onView(withId(R.id.detail_movie_date))
            .check(matches(withText("상영일: 3025.4.1 ~ 3025.4.25")))
    }

    @Test
    @DisplayName("영화 러닝타임은 '러닝타임: 152분'이다")
    fun movieTimeIs152minute() {
        onView(withId(R.id.scrollView))
            .perform(swipeUp())
        onView(withId(R.id.detail_movie_time))
            .check(matches(withText("러닝타임: 152분")))
    }

    @Test
    fun dateSpinnerCanSelectFirstItem() {
        onView(withId(R.id.scrollView))
            .perform(swipeUp())
        onView(withId(R.id.detail_spinner_date))
            .perform(click())
        onView(withText("3025-04-25"))
            .perform(click())
        onView(withText("3025-04-25"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun timeSpinnerCanSelectFirstItem() {
        onView(withId(R.id.scrollView))
            .perform(swipeUp())
        onView(withId(R.id.detail_spinner_time))
            .perform(click())
        onView(withText("12:00"))
            .perform(click())
        onView(withText("12:00"))
            .check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("기본 인원은 1명이다")
    fun defaultPersonnelIs1() {
        onView(withId(R.id.scrollView))
            .perform(swipeUp())
        onView(withId(R.id.detail_personnel))
            .check(matches(withText("1")))
    }

    @Test
    @DisplayName("인원을 추가하면 2명이다")
    fun addedPersonnelIs2() {
        onView(withId(R.id.scrollView))
            .perform(swipeUp())
        onView(withId(R.id.increment_button))
            .perform(click())
        onView(withId(R.id.detail_personnel))
            .check(matches(withText("2")))
    }

    @Test
    @DisplayName("인원을 두 번 추가하고 한 번 제외하면 2명이다")
    fun add2minus1PersonnelIs2() {
        onView(withId(R.id.scrollView))
            .perform(swipeUp())
        onView(withId(R.id.increment_button))
            .perform(click())
        onView(withId(R.id.increment_button))
            .perform(click())
        onView(withId(R.id.decrement_button))
            .perform(click())
        onView(withId(R.id.detail_personnel))
            .check(matches(withText("2")))
    }

    @Test
    @DisplayName("'선택 완료' 버튼이 보여야 한다")
    fun reservationButton() {
        onView(withId(R.id.scrollView))
            .perform(swipeUp())
        onView(withId(R.id.detail_reservation_button))
            .check(matches(withText("선택 완료")))
    }

    @Test
    @DisplayName("'선택 완료' 버튼을 누르면 다이얼로그의 '예매 확인'이 뜬다")
    fun dialogPopUpTitleWhenReservationButtonIsClicked() {
        onView(withId(R.id.scrollView))
            .perform(swipeUp())
        onView(withId(R.id.detail_reservation_button))
            .perform(click())
        onView(withText("예매 확인"))
            .check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("'선택 완료' 버튼을 누르면 다이얼로그의 '정말 예매하시겠습니까?'가 뜬다")
    fun dialogPopUpDetailWhenReservationButtonIsClicked() {
        onView(withId(R.id.scrollView))
            .perform(swipeUp())
        onView(withId(R.id.detail_reservation_button))
            .perform(click())
        onView(withText("정말 예매하시겠습니까?"))
            .check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("'선택 완료' 버튼을 누르면 다이얼로그에 취소 버튼이 뜬다")
    fun dialogPopUpCancelButtonWhenReservationButtonIsClicked() {
        onView(withId(R.id.scrollView))
            .perform(swipeUp())
        onView(withId(R.id.detail_reservation_button))
            .perform(click())
        onView(withText("취소"))
            .check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("'선택 완료' 버튼을 누르면 다이얼로그에 확인 버튼이 뜬다")
    fun dialogPopUpConfirmButtonWhenReservationButtonIsClicked() {
        onView(withId(R.id.scrollView))
            .perform(swipeUp())
        onView(withId(R.id.detail_reservation_button))
            .perform(click())
        onView(withText("예매 완료"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun informationMustBeMaintainedWhenScreenRotated() {
        onView(withId(R.id.scrollView))
            .perform(swipeUp())
        onView(withId(R.id.increment_button))
            .perform(click())
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        onView(withId(R.id.detail_personnel))
            .check(matches(withText("2")))
    }
}

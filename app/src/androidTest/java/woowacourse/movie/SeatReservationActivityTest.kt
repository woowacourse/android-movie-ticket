package woowacourse.movie

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotSelected
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.domain.Ticket
import woowacourse.movie.ui.seatreservation.SeatReservationActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SeatReservationActivityTest {
    private val intent = SeatReservationActivity.getIntent(
        ApplicationProvider.getApplicationContext(),
        Ticket(MOVIE.toLong(), TIME, TICKET_COUNT),
    )

    // 테스트 대상 Activity를 지정
    @get:Rule
    val activityRule = ActivityScenarioRule<SeatReservationActivity>(intent)

    @Test
    fun 영화_해리_포터와_마법사의_돌_선택_했을_때_제목텍스트는_해리_포터와_마법사의_돌이다() {
        // given:
        // when: 해당 뷰 실행 시
        val actual = onView(withId(R.id.tv_seat_reservation_title))

        // then: 화면에 영화 제목이 표기된다
        actual.check(matches(withText(MOVIE_TITLE)))
    }

    @Test
    fun A1을_선택하면_Selected가_활성화된다() {
        // given: A1
        val seatA1 = onView(withText(R.string.tv_seat_reservation_a1))

        // when: 좌석 클릭 시
        val actual = seatA1.perform(click())

        // then: 좌석 색상이 변경 된다
        actual.check(
            matches(
                isSelected(),
            ),
        )
    }

    @Test
    fun A1을_다시_선택하면_Selected가_비활성화_된다() {
        // given: A1 클릭 된 상태
        val seatA1 = onView(withText(R.string.tv_seat_reservation_a1)).perform(click())

        // when: 좌석 클릭 시
        val actual = seatA1.perform(click())

        // then: 좌석 색상이 변경 된다
        actual.check(
            matches(
                isNotSelected(),
            ),
        )
    }

    @Test
    fun A1_A2를_선택_시_총계는_14000원이다() {
        // given: A1, A2
        val total = onView(withId(R.id.tv_seat_reservation_price))
        val seatA1 = onView(withText(R.string.tv_seat_reservation_a1))
        val seatA2 = onView(withText(R.string.tv_seat_reservation_a2))

        // when: 좌석 클릭 시
        seatA1.perform(click())
        seatA2.perform(click())

        // then: 총액은 14000원으로 표기된다.
        total.check(matches(withText(TOTAL_ON_DISCOUNT_POLICY)))
    }

    @Test
    fun 영화_티켓_수_만큼_좌석을_선택하면_확인_버튼이_활성화_된다() {
        // given: A1, A2, A3
        val checkButton = onView(withId(R.id.tv_seat_reservation_check_btn))
        val seatA1 = onView(withText(R.string.tv_seat_reservation_a1))
        val seatA2 = onView(withText(R.string.tv_seat_reservation_a2))
        val seatA3 = onView(withText(R.string.tv_seat_reservation_a3))

        // when: 티켓 수 만큼, 좌석 클릭 시
        seatA1.perform(click())
        seatA2.perform(click())
        seatA3.perform(click())

        // then: 확인 버튼이 활성화 된다
        checkButton.check(
            matches(
                isEnabled(),
            ),
        )
    }

    @Test
    fun 확인_버튼이_활성화_되었을_때_선택된_좌석을_해제하면_다시_비활성화_된다() {
        // given: 확인 버튼 활성화
        val checkButton = onView(withId(R.id.tv_seat_reservation_check_btn))
        val seatA1 = onView(withText(R.string.tv_seat_reservation_a1)).perform(click())
        onView(withText(R.string.tv_seat_reservation_a2)).perform(click())
        onView(withText(R.string.tv_seat_reservation_a3)).perform(click())

        // when: A1 좌석 선택 해제 시
        seatA1.perform(click())

        // then: 확인 버튼이 비활성화 된다
        checkButton.check(
            matches(
                isNotEnabled(),
            ),
        )
    }

    @Test
    fun 확인_버튼_클릭_시_다이얼로그가_생성된다() {
        // given: 확인 버튼이 활성화 된 상태에서
        val checkButton = onView(withId(R.id.tv_seat_reservation_check_btn))
        onView(withText(R.string.tv_seat_reservation_a1)).perform(click())
        onView(withText(R.string.tv_seat_reservation_a2)).perform(click())
        onView(withText(R.string.tv_seat_reservation_a3)).perform(click())

        // when: 확인 버튼 클릭 시
        checkButton.perform(click())

        // then: 다이얼로그가 생성된다
        onView(withText(CONTENT)).check(matches(isDisplayed()))
    }

    companion object {
        private const val MOVIE = 1
        private val TIME = LocalDateTime.of(LocalDate.MAX, LocalTime.MAX)
        private const val CONTENT = "정말 예매하시겠습니까?"
        private const val MOVIE_TITLE = "해리 포터와 마법사의 돌"
        private const val TICKET_COUNT = 3
        private const val TOTAL_ON_DISCOUNT_POLICY = "14000원"
    }
}

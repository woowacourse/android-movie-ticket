package woowacourse.movie.seatSelection

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.checkIsDisplayed
import woowacourse.checkIsEnabled
import woowacourse.movie.MOVIE_TO_RESERVE
import woowacourse.movie.R
import woowacourse.movie.fakeContext
import woowacourse.movie.view.seatSelection.SeatSelectionActivity
import woowacourse.performClick

class SeatSelectionActivityTest {
    private lateinit var intent: Intent

    @Before
    fun setup() {
        intent =
            Intent(
                fakeContext,
                SeatSelectionActivity::class.java,
            ).apply {
                putExtra("movieReserve", MOVIE_TO_RESERVE)
            }
        ActivityScenario.launch<SeatSelectionActivity>(intent)
    }

    @Test
    fun 확인_버튼을_누르면_예매_확인_다이얼로그에_타이틀이_표시된다() {
        // given: 좌석 선택 페이지에서 좌석 선택 후
        onView(withText("A1")).performClick()

        onView(withText("A2")).performClick()

        // when: 확인 버튼을 누르면
        onView(withId(R.id.tv_seat_selection_complete_button)).performClick()

        // then: 예매 확인 다이얼로그에 타이틀이 표시된다
        onView(withText("예매 확인")).checkIsDisplayed()
    }

    @Test
    fun 확인_버튼을_누르면_예매_확인_다이얼로그에_메시지가_표시된다() {
        // given: 좌석 선택 페이지에서 좌석 선택 후
        onView(withText("A1")).performClick()

        onView(withText("A2")).performClick()

        // when: 확인 버튼을 누르면
        onView(withId(R.id.tv_seat_selection_complete_button)).performClick()

        // then: 예약 확인 다이얼로그에 메시지가 표시된다
        onView(withText("정말 예매하시겠습니까?")).checkIsDisplayed()
    }

    @Test
    fun 확인_버튼을_누르면_예매_확인_다이얼로그에_취소_버튼이_표시된다() {
        // given: 좌석 선택 페이지에서 좌석 선택 후
        onView(withText("A1")).performClick()

        onView(withText("A2")).performClick()

        // when: 확인 버튼을 누르면
        onView(withId(R.id.tv_seat_selection_complete_button)).performClick()

        // then: 예약 확인 다이얼로그에 메시지가 표시된다
        onView(withText("취소")).checkIsDisplayed()
    }

    @Test
    fun 완료_버튼을_누르면_예매_확인_다이얼로그에_예매완료_버튼이_표시된다() {
        // given: 좌석 선택 페이지에서 좌석 선택 후
        onView(withText("A1")).performClick()

        onView(withText("A2")).performClick()

        // when: 확인 버튼을 누르면
        onView(withId(R.id.tv_seat_selection_complete_button)).performClick()

        // then: 예약 확인 다이얼로그에 예매 완료 버튼이 표시된다
        onView(withText("예매 완료")).checkIsDisplayed()
    }

    @Test
    fun 좌석을_선택하면_선택한_좌석에_대해_총_금액이_표시된다() {
        // given: 좌석 선택 페이지에서
        // when: 좌석을 선택하면
        onView(withText("A1")).performClick()

        onView(withText("A2")).performClick()

        // then: 선택한 좌석에 대해 총 금액이 표시된다
        onView(withText("20,000원")).checkIsDisplayed()
    }

    @Test
    fun 좌석_선택뷰가_뜨면_예약할_영화_정보에_대한_영화_제목이_표시된다() {
        // given:
        // when: 좌석 선택뷰가 뜨면
        // then: 예약할_영화_정보에_대한_영화_제목이_표시된다
        onView(withText("라라랜드")).checkIsDisplayed()
    }

    @Test
    fun 예매할_좌석만큼_좌석을_선택하지_않으면_확인_버튼이_활성화_되지_않는다() {
        // given: 좌석 선택뷰가 뜨고
        // when: 예매할 좌석만큼 좌석을 선택하지 않으면
        onView(withText("A2")).performClick()

        // then: 확인 버튼이 활성화 되지 않는다
        onView(withText("확인")).checkIsDisplayed()
    }

    @Test
    fun 예매할_좌석만큼_좌석을_선택하면_확인_버튼이_활성화_된다() {
        // given: 좌석 선택뷰가 뜨고
        // when: 예매할 좌석만큼 좌석을 선택하면
        onView(withText("A2")).performClick()

        onView(withText("C2")).performClick()

        // then: 확인 버튼이 활성화 된다.
        onView(withText("확인")).checkIsDisplayed()
    }

    @Test
    fun 예매할_좌석만큼_좌석을_선택하면_선택한_좌석이_아닌_좌석은_클릭이_비활성화_된다() {
        // given: 좌석 선택뷰가 뜨고
        // when: 예매할 좌석만큼 좌석을 선택하면
        onView(withText("A2")).performClick()

        onView(withText("C2")).performClick()

        // then: 선택한_좌석이_아닌_좌석은_클릭이_비활성화_된다
        onView(withText("C3")).checkIsEnabled(false)

        onView(withText("D1")).checkIsEnabled(false)
    }
}

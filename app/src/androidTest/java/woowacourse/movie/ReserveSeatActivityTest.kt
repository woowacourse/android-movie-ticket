package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotSelected
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.domain.model.model.Payment
import com.example.domain.model.model.ReservationInfo
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.activity.ReserveSeatActivity
import woowacourse.movie.mapper.toReservationInfoModel
import java.time.LocalDate
import java.time.LocalTime

class ReserveSeatActivityTest {

    private val reservationInfoModel = ReservationInfo(
        "해리포터 2",
        LocalDate.of(2023, 3, 1),
        LocalTime.of(9, 0),
        3,
        Payment.ON_SITE
    ).toReservationInfoModel()

    private val intent = Intent(
        ApplicationProvider.getApplicationContext(),
        ReserveSeatActivity::class.java
    ).putExtra("reservationInfo", reservationInfoModel)

    @get:Rule
    val activityRule = ActivityScenarioRule<ReserveSeatActivity>(intent)

    @Test
    fun 자리를_클릭하면_자리가_선택된다() {
        // given

        // when: 자리를 클릭하면
        onView(withText("A2")).perform(click())

        // then: 자리가 선택된다
        onView(withText("A2")).check(matches(isSelected()))
    }

    @Test
    fun 선택된_자리를_한번_더_클릭하면_해제된다() {
        // given

        // when: 자리를 클릭하면
        onView(withText("A2")).perform(click()).perform(click())

        // then: 자리가 선택된다
        onView(withText("A2")).check(matches(isNotSelected()))
    }

    @Test
    fun 정해진_인원만큼_자리를_고르지_않으면_버튼이_비활성화_된다() {
        // given: 정해진 인원이 3명일 때

        // when: 자리를 2개만 고르면
        onView(withText("A2")).perform(click())
        onView(withText("B2")).perform(click())

        // then: 버튼이 비활성화 된다
        onView(withId(R.id.btn_reserve)).check(matches(not(isEnabled())))
    }

    @Test
    fun 정해진_인원만큼_자리를_고르면_버튼이_활성화_된다() {
        // given: 정해진 인원이 3명일 때

        // when: 자리를 3개 고르면
        onView(withText("A2")).perform(click())
        onView(withText("B2")).perform(click())
        onView(withText("C2")).perform(click())

        // then: 버튼이 활성화 된다
        onView(withId(R.id.btn_reserve)).check(matches(isEnabled()))
    }

    @Test
    fun 자리를_선택할_때_마다_가격이_합산된다() {
        // given: 맨 앞열의 가격이 8000원 일 때

        // when: 맨 앞열 자리를 하나 선택하면
        onView(withText("A2")).perform(click())

        // then: 8000원이 된다
        onView(withId(R.id.text_price)).check(matches(withText("8000")))

        // when: 맨 앞열 자리를 하나 더 선택하면
        onView(withText("A3")).perform(click())

        // then: 16000원이 된다
        onView(withId(R.id.text_price)).check(matches(withText("16000")))
    }

    @Test
    fun 자리_선택을_해제하면_가격이_감소한다() {
        // given: 자리를 두개 고르고 총 가격이 16000원인 상태일 때
        onView(withText("A2")).perform(click())
        onView(withText("A3")).perform(click())

        // when: 자리 하나를 취소하면
        onView(withText("A2")).perform(click())

        // then: 8000원이 된다
        onView(withId(R.id.text_price)).check(matches(withText("8000")))
    }
}

package woowacourse.movie

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReservationDetailActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(ReservationDetailActivity::class.java)

    @Test
    fun `빼기_버튼을_누르면_값이_감소한다`() {
        onView(withId(R.id.button_reservation_detail_minus))
            .perform(click())

        onView(withId(R.id.text_view_reservation_detail_number_of_tickets))
            .check(matches(withText("1")))
    }

    @Test
    fun `더하기_버튼을_누르면_값이_증가한다`() {
        onView(withId(R.id.button_reservation_detail_plus))
            .perform(click())

        onView(withId(R.id.text_view_reservation_detail_number_of_tickets))
            .check(matches(withText("2")))
    }

    @Test
    fun `예매_완료_버튼을_누르면_예매_완료_화면으로_이동한다`() {
        ActivityScenario.launch(ReservationDetailActivity::class.java)
        onView(withId(R.id.button_reservation_detail_finished)).perform(click())
        onView(withId(R.id.constraint_layout_reservation_finished)).check(matches(isDisplayed()))
    }

    @Test
    fun `예매_완료_버튼을_누른_뒤_예매_완료_화면의_뒤로_가기_버튼을_누르면_홈_화면으로_돌아온다`() {
        ActivityScenario.launch(ReservationDetailActivity::class.java)
        onView(withId(R.id.button_reservation_detail_finished)).perform(click())
        onView(withId(R.id.constraint_layout_reservation_finished)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.constraint_layout_reservation_home)).check(matches(isDisplayed()))
    }
}

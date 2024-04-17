package woowacourse.movie

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReservationHomeActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(ReservationHomeActivity::class.java)

    @Test
    fun `영화_목록에서_첫번째_아이템의_타이틀을_보여준다`() {
        onData(
            anything(),
        ).inAdapterView(withId(R.id.list_view_reservation_home)).atPosition(0).onChildView(withId(R.id.text_view_title)).check(
            matches(withText("해리 포터와 마법사의 돌")),
        )
    }

    @Test
    fun `영화_목록에서_첫번째_아이템의_상영일을_보여준다`() {
        onData(
            anything(),
        ).inAdapterView(withId(R.id.list_view_reservation_home)).atPosition(0).onChildView(withId(R.id.text_view_screening_date)).check(
            matches(withText("2001.11.14")),
        )
    }

    @Test
    fun `영화_목록에서_첫번째_아이템의_상영시간을_보여준다`() {
        onData(
            anything(),
        ).inAdapterView(withId(R.id.list_view_reservation_home)).atPosition(0).onChildView(withId(R.id.text_view_running_time)).check(
            matches(withText("152분")),
        )
    }

    @Test
    fun `영화_목록에서_첫번째_아이템의_버튼을_눌렀을_때_예약_상세_화면으로_이동`() {
        onData(anything()).inAdapterView(withId(R.id.list_view_reservation_home)).atPosition(0).onChildView(
            withId(R.id.button_reservation),
        ).perform(click())

        onView(withId(R.id.constraint_layout_reservation_detail))
            .check(matches(isDisplayed()))
    }
}

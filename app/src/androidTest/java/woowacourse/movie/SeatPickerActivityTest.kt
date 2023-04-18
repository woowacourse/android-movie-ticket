package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isNotSelected
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.ui.activity.SeatPickerActivity
import woowacourse.movie.ui.model.MovieTicketModel
import woowacourse.movie.ui.model.PeopleCountModel
import woowacourse.movie.ui.model.TicketTimeModel
import java.time.LocalDateTime

class SeatPickerActivityTest {
    private val ticket = MovieTicketModel(
        "글로의 50가지 그림자",
        TicketTimeModel(LocalDateTime.now()),
        PeopleCountModel(1),
        0
    )
    private val intent = Intent(ApplicationProvider.getApplicationContext(), SeatPickerActivity::class.java)
        .putExtra("ticket", ticket)

    @get:Rule
    val activityRule = ActivityScenarioRule<SeatPickerActivity>(intent)

    @Test
    fun 사용자가_빈_좌석을_누르면_좌석이_선택된다() {
        onView(withId(R.id.seat_view_1))
            .check(matches(isNotSelected()))
            .perform(click())

        onView(withId(R.id.seat_view_1))
            .check(matches(isSelected()))
    }

    @Test
    fun 사용자가_이미_선택된_좌석을_누르면_좌석_선택이_해제된다() {
        onView(withId(R.id.seat_view_1))
            .check(matches(isNotSelected()))
            .perform(click())

        onView(withId(R.id.seat_view_1))
            .check(matches(isSelected()))
            .perform(click())

        onView(withId(R.id.seat_view_1))
            .check(matches(isNotSelected()))
    }

    @Test
    fun 좌석을_모두_선택하면_더_이상_좌석_선택이_불가능하다() {
        onView(withId(R.id.seat_view_1))
            .check(matches(isNotSelected()))
            .perform(click())

        onView(withId(R.id.seat_view_2))
            .check(matches(isNotSelected()))
            .perform(click())

        onView(withId(R.id.seat_view_1))
            .check(matches(isSelected()))
        onView(withId(R.id.seat_view_2))
            .check(matches(isNotSelected()))
    }
}

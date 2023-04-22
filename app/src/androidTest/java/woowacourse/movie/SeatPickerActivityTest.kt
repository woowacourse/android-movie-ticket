package woowacourse.movie

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotClickable
import androidx.test.espresso.matcher.ViewMatchers.isNotSelected
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.ui.activity.SeatPickerActivity
import woowacourse.movie.ui.model.MovieTicketModel
import woowacourse.movie.ui.model.PeopleCountModel
import woowacourse.movie.ui.model.PriceModel
import woowacourse.movie.ui.model.TicketTimeModel
import java.time.LocalDateTime

class SeatPickerActivityTest {
    private val ticket = MovieTicketModel(
        "글로의 50가지 그림자",
        TicketTimeModel(LocalDateTime.now()),
        PeopleCountModel(1),
        emptySet(),
        PriceModel(0)
    )
    private val intent = SeatPickerActivity.createIntent(ApplicationProvider.getApplicationContext(), ticket)

    @get:Rule
    val activityRule = ActivityScenarioRule<SeatPickerActivity>(intent)

    @Test
    fun 사용자가_빈_좌석을_선택하면_좌석이_선택된다() {
        onView(withId(R.id.seat_view_1))
            .check(matches(isNotSelected()))
            .perform(click())

        onView(withId(R.id.seat_view_1))
            .check(matches(isSelected()))
    }

    @Test
    fun 사용자가_이미_선택된_좌석을_선택하면_좌석_선택이_해제된다() {
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

    @Test
    fun 좌석을_모두_선택하면_확인_버튼_클릭이_가능하다() {
        onView(withId(R.id.seat_picker_done_button))
            .check(matches(isNotClickable()))

        onView(withId(R.id.seat_view_1))
            .check(matches(isNotSelected()))
            .perform(click())

        onView(withId(R.id.seat_picker_done_button))
            .check(matches(isClickable()))
    }

    @Test
    fun 좌석을_모두_선택한_후_선택을_취소하면_확인_버튼_클릭이_불가능하다() {
        onView(withId(R.id.seat_view_1))
            .check(matches(isNotSelected()))
            .perform(click())
        onView(withId(R.id.seat_picker_done_button))
            .check(matches(isClickable()))

        onView(withId(R.id.seat_view_1))
            .check(matches(isSelected()))
            .perform(click())

        onView(withId(R.id.seat_picker_done_button))
            .check(matches(isNotClickable()))
    }

    @Test
    fun 좌석을_선택하면_가격이_업데이트_된다() {
        onView(withId(R.id.seat_view_1))
            .check(matches(isNotSelected()))
            .perform(click())

        onView(withId(R.id.seat_picker_price))
            .check(matches(not(withText("0원"))))
    }

    @Test
    fun 좌석이_선택된_상태에서_좌석을_취소하면_가격이_다시_0이_된다() {
        onView(withId(R.id.seat_view_1))
            .check(matches(isNotSelected()))
            .perform(click())

        onView(withId(R.id.seat_view_1))
            .check(matches(isSelected()))
            .perform(click())

        onView(withId(R.id.seat_picker_price))
            .check(matches(withText("0원")))
    }

    @Test
    fun 좌석을_선택하고_확인_버튼을_누르면_예매_확인_다이얼로그가_표시된다() {
        onView(withId(R.id.seat_view_1))
            .check(matches(isNotSelected()))
            .perform(click())

        onView(withId(R.id.seat_picker_done_button))
            .perform(click())

        onView(withText(R.string.dialog_title_seat_selection_check))
            .check(matches(isDisplayed()))
        onView(withText(R.string.dialog_message_seat_selection_check))
            .check(matches(isDisplayed()))
    }
}

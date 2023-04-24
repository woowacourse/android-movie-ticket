package woowacourse.movie

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.hasTextColor
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotSelected
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.model.BookedMovie
import woowacourse.movie.ui.seat.SeatActivity
import java.time.LocalDateTime

class SeatActivityTest {
    private val bookedMovie = BookedMovie(1, 0, 2, LocalDateTime.of(2024, 3, 1, 10, 0))

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<SeatActivity> =
        ActivityScenarioRule(
            SeatActivity.getIntent(
                ApplicationProvider.getApplicationContext(),
                bookedMovie,
            ),
        )

    @Test
    fun 상단에_스크린_글자가_뜨는지_확인한다() {
        onView(withText("SCREEN"))
    }

    @Test
    fun 올바른_영화제목인지_확인한다() {
        val title: String = "해리 포터와 마법사의 돌"
        onView(withId(R.id.textSeatMovieTitle)).check(matches(withText(title)))
    }

    @Test
    fun 좌석_행의_개수가_올바른지_확인한다() {
        onView(withId(R.id.seatTableLayout)).check(matches(hasChildCount(5)))
    }

    @Test
    fun 좌석_열의_개수가_올바른지_확인한다() {
        // row의 아이디는 commonId ~ commonId+4
        val commonId: Int = R.string.seat_table_row
        onView(withId(commonId + 4)).check(matches(hasChildCount(4)))
    }

    @Test
    fun 좌석등급에_따라_맞는_색이_들어갔는지_확인한다() {
        onView(withText("A1")).check(matches(hasTextColor(R.color.purple_400)))
        onView(withText("B1")).check(matches(hasTextColor(R.color.purple_400)))
        onView(withText("C1")).check(matches(hasTextColor(R.color.green_400)))
        onView(withText("D1")).check(matches(hasTextColor(R.color.green_400)))
        onView(withText("E1")).check(matches(hasTextColor(R.color.blue_500)))
    }

    @Test
    fun 좌석을_클릭하면_selected가_true이고_한번_더_클릭하면_selected가_false이다() {
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("A1")).check(matches(isSelected()))
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("A1")).check(matches(isNotSelected()))
    }

    @Test
    fun 아무것도_누르지_않았을때_결제액은_0원이다() {
        onView(withId(R.id.textSeatPayment)).check(matches(withText("0원")))
    }

    @Test
    fun 좌석을_선택하면_결재액이_0원이_아니다() {
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("B1")).perform(ViewActions.click())
        onView(withText("C1")).perform(ViewActions.click())

        onView(withId(R.id.textSeatPayment)).check(matches(not(withText("0원"))))
    }

    @Test
    fun 아무것도_누르지_않았을때_확인_버튼은_notEnabled이다() {
        onView(withId(R.id.buttonSeatConfirm)).check(matches(isNotEnabled()))
    }

    @Test
    fun 티켓_수_만큼_좌석을_누르면_확인_버튼이_enabled_된다() {
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("B1")).perform(ViewActions.click())
        onView(withText("C1")).perform(ViewActions.click())

        onView(withId(R.id.buttonSeatConfirm)).check(matches(isEnabled()))
    }

    @Test
    fun 티켓_수_만큼_좌석을_클릭되었을때_좌석을_한번_더_클릭하면_확인_버튼이_notEnabled_된다() {
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("B1")).perform(ViewActions.click())
        onView(withText("C1")).perform(ViewActions.click())
        onView(withId(R.id.buttonSeatConfirm)).check(matches(isEnabled()))

        onView(withText("A1")).perform(ViewActions.click())
        onView(withId(R.id.buttonSeatConfirm)).check(matches(isNotEnabled()))
    }
}

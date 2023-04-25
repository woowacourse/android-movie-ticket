package woowacourse.movie.presentation.view.seatpick

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.IntentFixture
import woowacourse.movie.R
import java.time.LocalDate

class SeatPickerActivityTest {
    private val intent =
        IntentFixture.getMovieInfoIntent(
            LocalDate.of(2023, 4, 23),
            LocalDate.of(2023, 5, 12),
            SeatPickerActivity::class.java
        )

    @get:Rule
    val activityRule = ActivityScenarioRule<SeatPickerActivity>(intent)

    @Test
    fun 현재_영화_이름을_출력한다() {
        onView(withId(R.id.tv_seat_picker_movie_title)).check(matches(withText("해리포터 - 2편")))
    }

    @Test
    fun 총_가격은_0으로_초기화된다() {
        onView(withId(R.id.tv_seat_picker_total_price)).check(matches(withText("0")))
    }

    @Test
    fun 좌석을_누르면_가격이_바뀐다() {
        onView(withText("A1")).perform(click())

        onView(withId(R.id.tv_seat_picker_total_price)).check(matches(withText("10000")))
    }
}

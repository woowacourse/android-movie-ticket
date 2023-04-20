package woowacourse.movie.view

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.domain.Minute
import com.example.domain.Movie
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.view.model.ReservationOptions
import woowacourse.movie.view.model.toUiModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@RunWith(AndroidJUnit4::class)
@LargeTest
class SeatSelectionActivityTest {

    private val reservationOptions = ReservationOptions(
        "해리 포터와 마법사의 돌",
        LocalDateTime.of(LocalDate.of(2024, 3, 1), LocalTime.of(13, 0)),
        2
    )

    private val movie = Movie(
        "해리 포터와 마법사의 돌",
        LocalDate.of(2024, 3, 1),
        LocalDate.of(2024, 3, 31),
        Minute(152),
        R.drawable.harry_potter1_poster,
        "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다."
    )

    private val intent = Intent(
        ApplicationProvider.getApplicationContext(),
        SeatSelectionActivity::class.java
    ).apply {
        putExtra("RESERVATION_OPTIONS", reservationOptions)
        putExtra("MOVIE", movie.toUiModel())
    }

    @get:Rule
    val activityRule = ActivityScenarioRule<SeatSelectionActivity>(intent)

    @Test
    fun movieTitle() {
        onView(withId(R.id.movie_title_textview))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun selectSeat() {
        onView(withText("A1")).perform(click()).check(matches(isSelected()))
    }

    @Test
    fun deselectSeat() {
        onView(withText("A1")).perform(click())
        onView(withText("A1")).perform(click()).check(matches(isNotSelected()))
    }

    @Test
    fun seatSelectionNotComplete() {
        onView(withText("A1")).perform(click())
        onView(withId(R.id.confirm_reservation_button))
            .check(matches(isNotEnabled()))
    }

    @Test
    fun seatSelectionComplete() {
        onView(withText("A1")).perform(click())
        onView(withText("A2")).perform(click())
        onView(withId(R.id.confirm_reservation_button))
            .check(matches(isEnabled()))
    }

    @Test
    fun whenSeatsAllSelectedExpectReservationFee() {
        onView(withText("A1")).perform(click())
        onView(withText("A2")).perform(click())
        onView(withId(R.id.reservation_fee_textview)).check(matches(withText("20,000원")))
    }

    @Test
    fun whenDeselectSeatThenResetReservationFee() {
        onView(withText("A1")).perform(click())
        onView(withText("A1")).perform(click())
        onView(withId(R.id.reservation_fee_textview)).check(matches(withText("0원")))
    }
}

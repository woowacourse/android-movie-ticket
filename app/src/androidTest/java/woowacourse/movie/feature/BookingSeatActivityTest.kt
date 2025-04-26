package woowacourse.movie.feature

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.domain.model.BookingInfo
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieDate
import woowacourse.movie.domain.model.MovieSeats
import woowacourse.movie.domain.model.MovieTime
import woowacourse.movie.domain.model.TicketCount
import woowacourse.movie.feature.bookingseat.view.BookingSeatActivity
import woowacourse.movie.feature.bookingseat.view.BookingSeatActivity.Companion.newIntent
import woowacourse.movie.feature.mapper.toUi

@Suppress("ktlint:standard:function-naming")
class BookingSeatActivityTest {
    private lateinit var activityScenario: ActivityScenario<BookingSeatActivity>

    @Before
    fun setup() {
        val intent =
            newIntent(
                context = getApplicationContext(),
                bookingInfo =
                    BookingInfo(
                        movie =
                            Movie(
                                title = "해리 포터와 마법사의 돌",
                                startDate = MovieDate(2025, 4, 1),
                                endDate = MovieDate(2025, 4, 25),
                                runningTime = 152,
                            ),
                        date = MovieDate(2025, 4, 1),
                        time = MovieTime(9, 0),
                        seats = MovieSeats(),
                        ticketCount = TicketCount(),
                    ).toUi(),
            )

        activityScenario = ActivityScenario.launch(intent)
    }

    @Test
    fun 좌석을_선택하면_총_금액이_바뀐다() {
        onView(withText("A1"))
            .perform(click())

        onView(withId(R.id.tv_booking_seat_movie_price))
            .check(matches(withText("10000원")))
    }

    @Test
    fun 좌석_없이_선택_완료_버튼을_누르면_완료_버튼이_눌리지_않는다() {
        onView(withId(R.id.tv_booking_seat_select_complete))
            .check(matches(isNotEnabled()))
    }

    @Test
    fun 선택_완료_버튼을_누르면_예매_확인_다이얼로그가_노출된다() {
        onView(withText("A1"))
            .perform(click())

        onView(withId(R.id.tv_booking_seat_select_complete))
            .perform(click())

        onView(withText("정말 예매하시겠습니까?"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 다이얼로그_외_영역을_터치해도_닫히지_않는다() {
        onView(withText("A1"))
            .perform(click())

        onView(withId(R.id.tv_booking_seat_select_complete))
            .perform(click())

        pressBack()

        onView(withText("정말 예매하시겠습니까?"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 영화_제목이_출력된다() {
        onView(withId(R.id.tv_booking_seat_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }
}

package woowacourse.movie.view.booking

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.fixture.fakeContext
import woowacourse.movie.view.booking.BookingActivity.Companion.KEY_BOOKING_DATE_TIME
import woowacourse.movie.view.booking.BookingActivity.Companion.KEY_BOOKING_MOVIE_TITLE
import woowacourse.movie.view.booking.BookingActivity.Companion.KEY_BOOKING_PEOPLE_COUNT
import woowacourse.movie.view.booking.BookingActivity.Companion.KEY_BOOKING_TICKET_PRICE

class BookingCompleteActivityTest {
    private lateinit var intent: Intent

    @Before
    fun setUp() {
        intent =
            Intent(
                fakeContext,
                BookingCompleteActivity::class.java,
            ).apply {
                putExtra(KEY_BOOKING_MOVIE_TITLE, "해리 포터와 마법사의 돌")
                putExtra(KEY_BOOKING_DATE_TIME, "2025.4.1 12:00")
                putExtra(KEY_BOOKING_PEOPLE_COUNT, 2)
                putExtra(KEY_BOOKING_TICKET_PRICE, 26000)
            }
        ActivityScenario.launch<BookingCompleteActivity>(intent)
    }

    @Test
    fun `전달_받은_영화_이름_상영_시간_예매_인원_예매_가격을_출력한다`() {
        onView(withText("해리 포터와 마법사의 돌")).check(matches(isDisplayed()))
        onView(withText("2025.4.1 12:00")).check(matches(isDisplayed()))
        onView(withText("일반 2명")).check(matches(isDisplayed()))
        onView(withText("26,000원 (현장 결제)")).check(matches(isDisplayed()))
    }
}

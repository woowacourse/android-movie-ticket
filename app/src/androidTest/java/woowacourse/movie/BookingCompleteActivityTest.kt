package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.fixture.fakeContext
import woowacourse.movie.view.booking.BookingActivity.Companion.KEY_BOOKING_DATE_TIME
import woowacourse.movie.view.booking.BookingActivity.Companion.KEY_BOOKING_MOVIE_TITLE
import woowacourse.movie.view.booking.BookingActivity.Companion.KEY_BOOKING_PEOPLE_COUNT
import woowacourse.movie.view.booking.BookingActivity.Companion.KEY_BOOKING_TICKET_PRICE
import woowacourse.movie.view.booking.BookingCompleteActivity

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
    fun `전달_받은_영화_이름을_출력한다`() {
        onView(withId(R.id.tv_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `전달_받은_상영_시간을_출력한다`() {
        onView(withId(R.id.tv_release_date)).check(matches(withText("2025.4.1 12:00")))
    }

    @Test
    fun `전달_받은_예매_인원을_출력한다`() {
        onView(withId(R.id.tv_people_count)).check(matches(withText("일반 2명")))
    }

    @Test
    fun `전달_받은_예매_가격을_출력한다`() {
        onView(withId(R.id.tv_price)).check(matches(withText("26,000원 (현장 결제)")))
    }
}

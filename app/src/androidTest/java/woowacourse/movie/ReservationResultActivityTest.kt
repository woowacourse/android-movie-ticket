package woowacourse.movie

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.fixture.MOVIE_NAME
import woowacourse.movie.fixture.SCREENING_DATE
import woowacourse.movie.fixture.TICKET_COUNT
import woowacourse.movie.fixture.TOTAL_COUNT
import woowacourse.movie.fixture.createReservation

class ReservationResultActivityTest {
    private lateinit var intent: Intent
    private val reservation = createReservation(MOVIE_NAME)

    @Before
    fun setUp() {
        val fakeActivity: Context = ApplicationProvider.getApplicationContext()
        intent =
            Intent(
                fakeActivity,
                ReservationResultActivity::class.java,
            ).apply {
                putExtra(KeyIdentifiers.KEY_RESERVATION, reservation)
            }

        ActivityScenario.launch<ReservationResultActivity>(intent)
    }

    @DisplayName("타이틀 글자 표시 테스트")
    @Test
    fun titleTest() {
        onView(withId(R.id.tv_title))
            .check(matches(withText(MOVIE_NAME)))
    }

    @DisplayName("상영일 글자 표시 테스트")
    @Test
    fun screeningDateTest() {
        onView(withId(R.id.tv_screening_date))
            .check(matches(withText(SCREENING_DATE)))
    }

    @DisplayName("예매 인원수 표시 테스트")
    @Test
    fun countTest() {
        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText(TICKET_COUNT)))
    }

    @DisplayName("구입 금액 표시 테스트")
    @Test
    fun totalPriceTest() {
        onView(withId(R.id.tv_total_price))
            .check(matches(withText(TOTAL_COUNT)))
    }
}

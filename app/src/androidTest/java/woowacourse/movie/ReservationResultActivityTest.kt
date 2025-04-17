package woowacourse.movie

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
import woowacourse.movie.domain.Reservation
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationResultActivityTest {
    private lateinit var intent: Intent
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    private val reservation =
        Reservation(
            title = "해리포터",
            count = 2,
            reservedTime =
                LocalDateTime.of(
                    LocalDate.parse("2025.04.30", formatter),
                    LocalTime.of(12, 0),
                ),
        )

    @Before
    fun setUp() {
        intent =
            Intent(
                ApplicationProvider.getApplicationContext(),
                ReservationResultActivity::class.java,
            ).apply {
                putExtra("reservation", reservation)
            }

        ActivityScenario.launch<ReservationResultActivity>(intent)
    }

    @DisplayName("타이틀 글자 표시 테스트")
    @Test
    fun titleTest() {
        onView(withId(R.id.tv_title))
            .check(matches(withText("해리포터")))
    }

    @DisplayName("상영일 글자 표시 테스트")
    @Test
    fun screeningDateTest() {
        onView(withId(R.id.tv_screening_date))
            .check(matches(withText("2025.04.30 12:00")))
    }

    @DisplayName("예매 인원수 표시 테스트")
    @Test
    fun countTest() {
        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText("2명")))
    }

    @DisplayName("구입 금액 표시 테스트")
    @Test
    fun totalPriceTest() {
        onView(withId(R.id.tv_total_price))
            .check(matches(withText("26,000원")))
    }
}

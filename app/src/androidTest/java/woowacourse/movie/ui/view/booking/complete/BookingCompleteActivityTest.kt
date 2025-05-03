package woowacourse.movie.ui.view.booking.complete

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.ui.model.booking.BookingResultUiModel

class BookingCompleteActivityTest {
    private lateinit var scenario: ActivityScenario<BookingCompleteActivity>
    private val bookingResultUiModel = mockBookingResultUiModel()

    @Before
    fun setUp() {
        Intents.init()

        val intent =
            BookingCompleteActivity.newIntent(
                ApplicationProvider.getApplicationContext(),
                bookingResultUiModel,
            )

        scenario = ActivityScenario.launch(intent)
    }

    @After
    fun tearDown() {
        Intents.release()
        scenario.close()
    }

    @Test
    fun `화면에_영화_제목이_표시된다`() {
        onView(withId(R.id.tv_complete_title)).check(
            matches(
                allOf(
                    withText("해리 포터와 마법사의 돌"),
                    isDisplayed(),
                ),
            ),
        )
    }

    @Test
    fun `화면에_선택한_영화_상영일이_표시된다`() {
        onView(withId(R.id.tv_complete_screening_date)).check(
            matches(
                allOf(
                    withText("2025.4.20"),
                    isDisplayed(),
                ),
            ),
        )
    }

    @Test
    fun `화면에_선택한_영화_상영시간이_표시된다`() {
        onView(withId(R.id.tv_complete_screening_time)).check(
            matches(
                allOf(
                    withText("12:00"),
                    isDisplayed(),
                ),
            ),
        )
    }

    @Test
    fun `화면에_선택한_영화_예매인원이_표시된다`() {
        onView(withId(R.id.tv_head_count)).check(
            matches(
                allOf(
                    withText("일반 2명"),
                    isDisplayed(),
                ),
            ),
        )
    }

    @Test
    fun `화면에_선택한_영화_결제금액이_표시된다`() {
        onView(withId(R.id.tv_booking_amount)).check(
            matches(
                allOf(
                    withText("26,000원 (현장 결제)"),
                    isDisplayed(),
                ),
            ),
        )
    }

    private fun mockBookingResultUiModel(): BookingResultUiModel {
        return BookingResultUiModel(
            title = "해리 포터와 마법사의 돌",
            headCount = "2",
            selectedDate = "2025.4.20",
            selectedTime = "12:00",
        )
    }
}

package woowacourse.movie.booking

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.model.data.Movie
import woowacourse.movie.ui.view.booking.BookingActivity
import woowacourse.movie.ui.view.booking.BookingSummaryActivity
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class BookingActivityTest {
    @Before
    fun setUp() {
        Intents.init()

        val movie =
            Movie(
                "Test",
                LocalDate.of(2025, 4, 17),
                LocalDate.of(2025, 4, 30),
                100,
                "match",
            )

        val intent =
            Intent(
                ApplicationProvider.getApplicationContext(),
                BookingActivity::class.java,
            ).apply {
                putExtra("Movie", movie)
            }

        ActivityScenario.launch<BookingActivity>(intent)
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @DisplayName("영화 제목이 출력된다")
    @Test
    fun titleTest() {
        onView(withId(R.id.title))
            .check(matches(withText("Test")))
    }

    @DisplayName("상영일자가 출력된다")
    @Test
    fun screeningDateTest() {
        onView(withId(R.id.screeningDate))
            .check(matches(withText("상영일: 2025-04-17 ~ 2025-04-30")))
    }

    @DisplayName("러닝타임이 출력된다")
    @Test
    fun runningTimeTest() {
        onView(withId(R.id.runningTime))
            .check(matches(withText("러닝타임: 100분")))
    }

    @DisplayName("증가 버튼을 누르면 숫자가 증가한다")
    @Test
    fun increaseButtonTest() {
        onView(withId(R.id.increase))
            .perform(click())

        onView(withId(R.id.headCount))
            .check(matches(withText("2")))
    }

    @DisplayName("감소 버튼을 누르면 숫자가 감소한다")
    @Test
    fun decreaseButtonTest() {
        onView(withId(R.id.increase))
            .perform(click())

        onView(withId(R.id.decrease))
            .perform(click())

        onView(withId(R.id.headCount))
            .check(matches(withText("1")))
    }

    @DisplayName("선택 완료 버튼을 누르면 다이얼로그가 나타난다")
    @Test
    fun dialogTest() {
        onView(withId(R.id.select))
            .perform(click())

        onView(withText("정말 예매하시겠습니까?"))
            .check(matches(isDisplayed()))
    }

    @DisplayName("예매 완료 버튼을 누르면 화면이 이동되고 예매 데이터가 전달된다")
    @Test
    fun intentTest() {
        onView(withId(R.id.select))
            .perform(click())

        onView(withText("예매 완료"))
            .perform(click())

        intended(hasComponent(BookingSummaryActivity::class.java.name))

        intended(
            allOf(
                hasExtraWithKey("Ticket"),
            ),
        )
    }
}

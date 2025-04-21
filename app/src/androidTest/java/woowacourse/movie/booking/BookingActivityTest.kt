package woowacourse.movie.booking

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.ui.view.booking.BookingActivity
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class BookingActivityTest {
    @Before
    fun setUp() {
        val movie =
            Movie(
                "승부",
                LocalDate.of(2025, 3, 26),
                LocalDate.of(2025, 4, 26),
                115,
                "match",
            )

        val intent =
            Intent(
                ApplicationProvider.getApplicationContext(),
                BookingActivity::class.java,
            ).apply {
                putExtra("Movie", movie.id)
            }

        ActivityScenario.launch<BookingActivity>(intent)
    }

    @DisplayName("영화 제목이 출력된다")
    @Test
    fun titleTest() {
        onView(withText("승부"))
            .check(matches(isDisplayed()))
    }

    @DisplayName("상영일자가 출력된다")
    @Test
    fun screeningDateTest() {
        onView(withText("상영일: 2025-03-26 ~ 2025-04-26"))
            .check(matches(isDisplayed()))
    }

    @DisplayName("러닝타임이 출력된다")
    @Test
    fun runningTimeTest() {
        onView(withText("러닝타임: 115분"))
            .check(matches(isDisplayed()))
    }

    @DisplayName("예매 수량의 처음 값은 1이다.")
    @Test
    fun headCountViewTest() {
        onView(withId(R.id.headCount))
            .check(matches(withText("1")))
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
}

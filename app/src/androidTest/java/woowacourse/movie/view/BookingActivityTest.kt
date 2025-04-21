package woowacourse.movie.view

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
import io.kotest.matchers.string.match
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.ui.view.BookingActivity
import woowacourse.movie.ui.view.BookingSummaryActivity
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class BookingActivityTest {
    @Before
    fun setUp() {
        Intents.init()

        val movie = Movie(
            "Test",
            LocalDate.of(2025, 4, 17),
            LocalDate.of(2025, 4, 30),
            100,
            R.drawable.match
        )

        val intent = Intent(
            ApplicationProvider.getApplicationContext(),
            BookingActivity::class.java
        ).apply {
            putExtra("Movie", movie)
        }

        ActivityScenario.launch<BookingActivity>(intent)
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun 영화_제목이_출력된다() {
        onView(withId(R.id.textview_title))
            .check(matches(withText("Test")))
    }

    @Test
    fun 상영일자가_출력된다() {
        onView(withId(R.id.textview_screeningdate))
            .check(matches(withText("상영일: 2025-04-17 ~ 2025-04-30")))
    }

    @Test
    fun 러닝타임이_출력된다() {
        onView(withId(R.id.textview_runningtime))
            .check(matches(withText("러닝타임: 100분")))
    }

    @Test
    fun 인원의_초기값은_1이다() {
        onView(withId(R.id.textview_headcount))
            .check(matches(withText("1")))
    }

    @Test
    fun 증가_버튼을_누르면_숫자가_1_증가한다() {
        onView(withId(R.id.textview_headcount))
            .check(matches(withText("1")))

        onView(withId(R.id.button_increase))
            .perform(click())

        onView(withId(R.id.textview_headcount))
            .check(matches(withText("2")))
    }

    @Test
    fun 값이_2_이상일때_감소_버튼을_누르면_숫자가_1_감소한다() {
        onView(withId(R.id.button_increase))
            .perform(click())

        onView(withId(R.id.textview_headcount))
            .check(matches(withText("2")))

        onView(withId(R.id.button_decrease))
            .perform(click())

        onView(withId(R.id.textview_headcount))
            .check(matches(withText("1")))
    }

    @Test
    fun 값이_1일때_감소_버튼을_누르면_숫자가_감소하지_않는다() {
        onView(withId(R.id.textview_headcount))
            .check(matches(withText("1")))

        onView(withId(R.id.button_decrease))
            .perform(click())

        onView(withId(R.id.textview_headcount))
            .check(matches(withText("1")))
    }

    @Test
    fun 선택완료_버튼을_누르면_다이얼로그가_나타난다() {
        onView(withId(R.id.button_select))
            .perform(click())

        onView(withText("정말 예매하시겠습니까?"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 예매완료_버튼을_누르면_화면이_이동되고_예매_데이터가_전달된다() {
        onView(withId(R.id.button_select))
            .perform(click())

        onView(withText("예매 완료"))
            .perform(click())

        intended(hasComponent(BookingSummaryActivity::class.java.name))

        intended(allOf(
            hasExtraWithKey("Ticket")
        ))
    }
}
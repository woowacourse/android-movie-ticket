package woowacourse.movie

import android.content.Intent
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.instanceOf
import org.junit.Before
import org.junit.Test

class BookingActivityTest {
    private lateinit var intent: Intent

    @Before
    fun setupIntent() {
        intent = Intent(ApplicationProvider.getApplicationContext(), BookingActivity::class.java).apply {
            putExtra("TITLE", "해리 포터와 마법사의 돌")
            putExtra("POSTER", R.drawable.harry_potter_poster)
            putExtra("START_DATE", "2025.4.1")
            putExtra("END_DATE", "2025.4.25")
            putExtra("RUNNING_TIME", "152분")
        }
    }

    @Test
    fun 증가_버튼을_누르면_카운트가_1씩_증가한다() {
        ActivityScenario.launch<BookingActivity>(intent).use {
            onView(withId(R.id.plus_button))
                .perform(click())

            onView(withId(R.id.ticket_count))
                .check(matches(withText("1")))
        }
    }

    @Test
    fun 감소_버튼을_누르면_카운트가_1씩_감소한다() {
        val scenario = ActivityScenario.launch<BookingActivity>(intent)

        scenario.onActivity { activity ->
            activity.findViewById<TextView>(R.id.ticket_count).text = "1"
        }

        scenario.use {
            onView(withId(R.id.minus_button))
                .perform(click())

            onView(withId(R.id.ticket_count))
                .check(matches(withText("0")))
        }
    }

    @Test
    fun 평일_날짜를_선택하면_10시부터_상영이_시작된다() {
        ActivityScenario.launch<BookingActivity>(intent).use {
            onView(withId(R.id.movie_date))
                .perform(click())

            onData(allOf(`is`(instanceOf(String::class.java)), `is`("2025.4.17")))
                .perform(click())

            onView(withId(R.id.movie_time))
                .check(matches(withSpinnerText("10:00")))
        }
    }

    @Test
    fun 주말_날짜를_선택하면_9시부터_상영이_시작된다() {
        ActivityScenario.launch<BookingActivity>(intent).use {
            onView(withId(R.id.movie_date))
                .perform(click())

            onData(allOf(`is`(instanceOf(String::class.java)), `is`("2025.4.19")))
                .perform(click())

            onView(withId(R.id.movie_time))
                .check(matches(withSpinnerText("09:00")))
        }
    }
}
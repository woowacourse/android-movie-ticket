package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class ReserveActivityTest {
    private lateinit var intent: Intent
    private val movie = HARRY_POTTER_MOVIE

    @Before
    fun setUp() {
        intent =
            Intent(
                ApplicationProvider.getApplicationContext(),
                ReserveActivity::class.java,
            ).apply {
                putExtra("movie", movie)
            }
        ActivityScenario.launch<ReserveActivity>(intent)
        buttonPlus()
    }

    private fun buttonPlus() {
        onView(withId(R.id.btn_plus))
            .perform(click())
    }

    @Test
    fun 타이틀_글자를_표시한다() {
        onView(withId(R.id.tv_title))
            .check(matches(withText("해리포터")))
    }

    @Test
    fun 상영일_글자를_표시한다() {
        onView(withId(R.id.tv_screening_date))
            .check(matches(withText("2025.04.30 ~ 2025.05.04")))
    }

    @Test
    fun 러닝타임_글자를_표시한다() {
        onView(withId(R.id.tv_running_time))
            .check(matches(withText("152분")))
    }

    @Test
    fun 예매_티켓_수_증가() {
        onView(withId(R.id.btn_plus))
            .perform(click())

        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText("3")))
    }

    @Test
    fun 예매_티켓_수_감소() {
        onView(withId(R.id.btn_minus))
            .perform(click())

        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText("1")))
    }
}

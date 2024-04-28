package woowacourse.movie.java

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.ticket.view.MovieTicketActivity

@RunWith(AndroidJUnit4::class)
class MovieTicketTest {
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieTicketActivity::class.java,
        ).also {
            it.putExtra("count", 2)
        }

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule<MovieTicketActivity>(intent)

    @Test
    fun `티켓의_총_금액이_표시되어야_한다`() {
        Espresso.onView(ViewMatchers.withId(R.id.ticket_price))
            .check(ViewAssertions.matches(ViewMatchers.withText("26,000원 (현장결제)")))
    }

    @Test
    fun `티켓의_총_인원이_표시되어야_한다`() {
        Espresso.onView(ViewMatchers.withId(R.id.ticket_number_of_people))
            .check(ViewAssertions.matches(ViewMatchers.withText("일반 2명")))
    }
}

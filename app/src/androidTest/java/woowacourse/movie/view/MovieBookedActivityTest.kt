package woowacourse.movie.view

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.MovieFixture

@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieBookedActivityTest {
    private lateinit var scenario: ActivityScenario<MovieBookedActivity>

    @Before
    fun setUp() {
        val fakeContext = ApplicationProvider.getApplicationContext<Context>()
        val intent =
            Intent(
                fakeContext,
                MovieBookedActivity::class.java,
            ).apply {
                putExtra("bookingStatus", MovieFixture.BOOKING_STATUS)
            }
        scenario = ActivityScenario.launch(intent)
    }

    @Test
    fun 예약정보가_올바르게_표시된다() {
        onView(withText(MovieFixture.HARRY_POTTER_TITLE)).check(matches(isDisplayed()))
        onView(withText(MovieFixture.BOOKING_DATETIME)).check(matches(isDisplayed()))
        onView(withText(MovieFixture.BOOKING_TICKET_COUNT)).check(matches(isDisplayed()))
        onView(withText(MovieFixture.BOOKING_TICKET_PRICE)).check(matches(isDisplayed()))
    }
}

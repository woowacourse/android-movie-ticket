package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.activity.PurchaseConfirmationActivity

@RunWith(AndroidJUnit4::class)
class PurchaseConfirmationActivityTest {
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            PurchaseConfirmationActivity::class.java,
        ).also {
            it.putExtra("ReservationId", 0)
        }

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule<PurchaseConfirmationActivity>(intent)

    @Test
    fun titleTest() {
        Espresso.onView(withId(R.id.movie_title_confirmation))
            .check(matches(ViewMatchers.withText("default")))
    }

    @Test
    fun runningTimeTest() {
        Espresso.onView(withId(R.id.purchase_movie_running_time))
            .check(matches(ViewMatchers.withText("222분")))
    }

    @Test
    fun getFeeTest() {
        Espresso.onView(withId(R.id.ticket_charge))
            .check(matches(ViewMatchers.withText("price: 0")))
    }

    @Test
    fun pressBackTest() {
        Espresso.pressBack()
        Espresso.onView(withId(R.id.movies_list_item))
            .check(matches(ViewMatchers.isDisplayed()))
    }
}

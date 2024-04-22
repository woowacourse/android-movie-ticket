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
import woowacourse.movie.model.Reservation
import woowacourse.movie.model.screening.ScreeningDate
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.screening.Screening
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class PurchaseConfirmationActivityTest {
    private val movie =
        MovieInfo(
            Title("차람과 하디의 진지한 여행기"),
            ScreeningDate(LocalDate.of(2024, 2, 25)),
            RunningTime(230),
            Synopsis("wow!"),
        )
    private val screening = Screening(movie, 10000)
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            PurchaseConfirmationActivity::class.java,
        ).also {
            it.putExtra("Reservation", Reservation(screening, 3))
        }

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule<PurchaseConfirmationActivity>(intent)

    @Test
    fun titleTest() {
        Espresso.onView(withId(R.id.movie_title_confirmation))
            .check(matches(ViewMatchers.withText("차람과 하디의 진지한 여행기")))
    }

    @Test
    fun runningTimeTest() {
        Espresso.onView(withId(R.id.purchase_movie_running_time))
            .check(matches(ViewMatchers.withText("230분")))
    }

    @Test
    fun getFeeTest() {
        Espresso.onView(withId(R.id.ticket_charge))
            .check(matches(ViewMatchers.withText("price: 30000")))
    }

    @Test
    fun pressBackTest() {
        Espresso.pressBack()
        Espresso.onView(withId(R.id.movies_list_item))
            .check(matches(ViewMatchers.isDisplayed()))
    }
}

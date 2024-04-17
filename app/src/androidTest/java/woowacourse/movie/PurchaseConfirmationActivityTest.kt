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
import woowacourse.movie.model.movieInfo.MovieDate
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.theater.Theater
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class PurchaseConfirmationActivityTest {
    private val movie =
        MovieInfo(
            Title("차람과 하디의 진지한 여행기"),
            MovieDate(LocalDate.of(2024, 2, 25)),
            RunningTime(230),
            Synopsis("wow!")
        )
    private val theater = Theater(movie, 10000)
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            PurchaseConfirmationActivity::class.java,
        ).also {
            it.putExtra("Theater", theater)
            it.putExtra("ticketNum", 3)
        }

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule<PurchaseConfirmationActivity>(intent)

    @Test
    fun getFeeTest() {
        Espresso.onView(withId(R.id.ticket_charge))
            .check(matches(ViewMatchers.withText("price: 30000")))
    }
}
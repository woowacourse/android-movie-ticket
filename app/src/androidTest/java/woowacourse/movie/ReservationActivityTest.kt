package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.view.MainActivity.Companion.EXTRA_END_DAY
import woowacourse.movie.view.MainActivity.Companion.EXTRA_END_MONTH
import woowacourse.movie.view.MainActivity.Companion.EXTRA_END_YEAR
import woowacourse.movie.view.MainActivity.Companion.EXTRA_POSTER_ID
import woowacourse.movie.view.MainActivity.Companion.EXTRA_RUNNING_TIME
import woowacourse.movie.view.MainActivity.Companion.EXTRA_START_DAY
import woowacourse.movie.view.MainActivity.Companion.EXTRA_START_MONTH
import woowacourse.movie.view.MainActivity.Companion.EXTRA_START_YEAR
import woowacourse.movie.view.MainActivity.Companion.EXTRA_TITLE
import woowacourse.movie.view.ReservationActivity

class ReservationActivityTest {
    @get:Rule
    val activityRule =
        ActivityScenarioRule<ReservationActivity>(
            Intent(
                ApplicationProvider.getApplicationContext(),
                ReservationActivity::class.java,
            ).apply {
                putExtra(EXTRA_TITLE, "영화 제목")
                putExtra(EXTRA_START_YEAR, 2025)
                putExtra(EXTRA_START_MONTH, 4)
                putExtra(EXTRA_START_DAY, 16)
                putExtra(EXTRA_END_YEAR, 2025)
                putExtra(EXTRA_END_MONTH, 4)
                putExtra(EXTRA_END_DAY, 18)
                putExtra(
                    EXTRA_POSTER_ID,
                    R.drawable.poster_harry_potter_and_the_philosophers_stone,
                )
                putExtra(EXTRA_RUNNING_TIME, 152)
            },
        )

    @Test
    fun `포스터가_표시된다`() {
        onView(withId(R.id.iv_reservation_poster))
            .check(matches(isDisplayed()))
    }
}

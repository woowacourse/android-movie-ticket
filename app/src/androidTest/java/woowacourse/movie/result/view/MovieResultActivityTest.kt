package woowacourse.movie.result.view

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_ID
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_RESERVATION_COUNT

@RunWith(AndroidJUnit4::class)
class MovieResultActivityTest {
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieResultActivity::class.java,
        ).apply {
            putExtra(KEY_MOVIE_ID, 0L)
            putExtra(KEY_MOVIE_RESERVATION_COUNT, 3)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieResultActivity>(intent)

    @Test
    fun `예매한_영화의_제목이_표시된다`() {
        onView(withId(R.id.resultTitle))
            .check(matches(withText("타이타닉")))
    }

    @Test
    fun `예매한_영화의_상영일이_표시된다`() {
        onView(withId(R.id.resultDate))
            .check(matches(withText("2024.4.17")))
    }

    @Test
    fun `예매한_영화의_인원수_표시된다`() {
        onView(withId(R.id.resultReservCount))
            .check(matches(withText("3")))
    }

    @Test
    fun `예매한_영화의_가격이_표시된다`() {
        onView(withId(R.id.resultReservPrice))
            .check(matches(withText("39,000")))
    }
}

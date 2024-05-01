package woowacourse.movie.presentation.result

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_ID
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_SCREEN_DATE_TIME_ID
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_SEATS_ID_LIST

@RunWith(AndroidJUnit4::class)
class MovieResultActivityTest {
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieResultActivity::class.java,
        ).apply {
            putExtra(EXTRA_MOVIE_ID, 0L)
            putExtra(EXTRA_MOVIE_SCREEN_DATE_TIME_ID, 0L)
            putExtra(EXTRA_MOVIE_SEATS_ID_LIST, listOf(0L, 19L).toLongArray())
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
            .check(matches(withText("2024-04-01 10:00")))
    }

    @Test
    fun `예매한_영화의_인원수_표시된다`() {
        onView(withId(R.id.resultReservCount))
            .check(matches(withText("2")))
    }

    @Test
    fun `예매한_좌석이_표시된다`() {
        onView(withId(R.id.resultSeats))
            .check(matches(withText("A1,E4")))
    }

    @Test
    fun `좌석_등급에_맞게_예매한_영화의_가격이_표시된다`() {
        onView(withId(R.id.resultReservPrice))
            .check(matches(withText("25,000")))
    }
}

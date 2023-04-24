package woowacourse.movie.ui.movieselectseatactivity

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.ui.movieselectseatactivity.MovieSelectSeatActivity.Companion.BOOKED_SCREENING_DATE_TIME
import woowacourse.movie.ui.movieselectseatactivity.MovieSelectSeatActivity.Companion.MOVIE_DATA
import woowacourse.movie.ui.movieselectseatactivity.MovieSelectSeatActivity.Companion.TICKET_COUNT
import java.time.LocalDate
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class MovieSelectSeatActivityTest {

    private val dummyMovieData = MovieUIModel(
        title = "해리포터",
        screeningStartDay = LocalDate.parse("2023-03-01"),
        screeningEndDay = LocalDate.parse("2023-03-31"),
        runningTime = 30,
        description = ""
    )

    // 무비데이,조조 할인 적용시간
    private val dummyIntentData =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieSelectSeatActivity::class.java
        ).apply {
            putExtra(MOVIE_DATA, dummyMovieData)
            putExtra(TICKET_COUNT, 3)
            putExtra(BOOKED_SCREENING_DATE_TIME, LocalDateTime.parse("2023-03-10T09:00"))
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieSelectSeatActivity>(dummyIntentData)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun `영화_제목이_표시된다`() {
        onView(withId(R.id.tv_movie_title)).check(matches(withText("해리포터")))
    }
}

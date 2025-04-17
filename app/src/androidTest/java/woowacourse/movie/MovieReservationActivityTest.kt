package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.fixtures.fakeContext
import woowacourse.movie.fixtures.movie

class MovieReservationActivityTest {
    private lateinit var intent: Intent

    @get:Rule
    val activityRule = ActivityScenarioRule(MovieReservationActivity::class.java)

    @Before
    fun setUp() {
        intent =
            Intent(fakeContext, MainActivity::class.java).apply {
                putExtra("movie", movie)
            }
        ActivityScenario.launch<MovieReservationActivity>(intent)
    }

    @Test
    @DisplayName("선택한 영화의 제목이 표시된다")
    fun displaySelectedMovieTitleTest() {
        onView(withId(R.id.movie_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    @DisplayName("선택한_영화의_상영일이_표시된다")
    fun displaySelectedMovieScreeningDateTest() {
        onView(withId(R.id.screening_date)).check(matches(withText("2025.04.01 ~ 2025.04.25")))
    }

    @Test
    @DisplayName("선택한_영화의_러닝타임이_표시된다")
    fun displaySelectedMovieRunningTimeTest() {
        onView(withId(R.id.running_time)).check(matches(withText("152분")))
    }
}

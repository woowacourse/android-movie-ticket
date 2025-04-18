package woowacourse.movie

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.fixture.fakeContext
import woowacourse.movie.fixture.movie
import woowacourse.movie.view.reservation.MovieReservationActivity

class MovieReservationActivityTest {
    private lateinit var intent: Intent
    private lateinit var scenario: ActivityScenario<MovieReservationActivity>

    @get:Rule
    val activityRule = ActivityScenarioRule(MovieReservationActivity::class.java)

    @Before
    fun setUp() {
        intent =
            Intent(fakeContext, MovieReservationActivity::class.java).apply {
                putExtra("movie", movie)
            }
        scenario = ActivityScenario.launch(intent)
    }

    @Test
    @DisplayName("선택한 영화의 제목이 표시된다")
    fun displaySelectedMovieTitleTest() {
        onView(withId(R.id.movie_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    @DisplayName("선택한 영화의 상영일이 표시된다")
    fun displaySelectedMovieScreeningDateTest() {
        onView(withId(R.id.screening_date)).check(matches(withText("2025.04.01 ~ 2025.04.25")))
    }

    @Test
    @DisplayName("선택한 영화의 러닝타임이 표시된다")
    fun displaySelectedMovieRunningTimeTest() {
        onView(withId(R.id.running_time)).check(matches(withText("152분")))
    }

    @Test
    @DisplayName("+ 버튼을 클릭하면 인원수가 1 증가한다")
    fun increaseTicketCountOnIncrementButtonClickTest() {
        onView(withId(R.id.increment_button)).perform(click())
        onView(withId(R.id.ticket_count)).check(matches(withText("2")))
    }

    @Test
    @DisplayName("- 버튼을 클릭하면 인원수가 1 감소한다")
    fun decreaseTicketCountOnDecrementButtonClickTest() {
        onView(withId(R.id.increment_button)).perform(click())
        onView(withId(R.id.decrement_button)).perform(click())
        onView(withId(R.id.ticket_count)).check(matches(withText("1")))
    }

    @Test
    @DisplayName("선택 완료 버튼을 클릭하면 다이얼로그가 표시된다")
    fun displayAlertDialogOnSelectButtonClickTest() {
        onView(withId(R.id.select_button)).perform(click())
        onView(isRoot()).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("화면이 회전돼도 입력한 정보가 유지된다")
    fun retainInformationOnScreenRotateTest() {
        onView(withId(R.id.increment_button)).perform(click())
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        onView(withId(R.id.ticket_count)).check(matches(withText("2")))
    }
}

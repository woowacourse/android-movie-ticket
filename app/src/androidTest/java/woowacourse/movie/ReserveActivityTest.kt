package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.anything
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.fixture.FIRST_DATE
import woowacourse.movie.fixture.FIRST_TIME
import woowacourse.movie.fixture.MOVIE_NAME
import woowacourse.movie.fixture.RUNNING_TIME
import woowacourse.movie.fixture.SCREENING_PERIOD
import woowacourse.movie.fixture.createMovie
import woowacourse.movie.reserve.ReserveActivity

class ReserveActivityTest {
    private lateinit var intent: Intent
    private val movie = createMovie(MOVIE_NAME)
    private lateinit var scenario: ActivityScenario<ReserveActivity>

    @Before
    fun setUp() {
        val fakeActivity: Context = ApplicationProvider.getApplicationContext()
        intent =
            Intent(
                fakeActivity,
                ReserveActivity::class.java,
            ).apply {
                putExtra(KeyIdentifiers.KEY_MOVIE, movie)
            }

        scenario = ActivityScenario.launch(intent)
    }

    @DisplayName("타이틀 글자 표시 테스트")
    @Test
    fun titleTest() {
        onView(withId(R.id.tv_title))
            .check(matches(withText(MOVIE_NAME)))
    }

    @DisplayName("상영일 글자 표시 테스트")
    @Test
    fun screeningDateTest() {
        onView(withId(R.id.tv_screening_date))
            .check(matches(withText(SCREENING_PERIOD)))
    }

    @DisplayName("러닝타임 글자 표시 테스트")
    @Test
    fun runningTimeTest() {
        onView(withId(R.id.tv_running_time))
            .check(matches(withText(RUNNING_TIME)))
    }

    @DisplayName("예매 티켓 수 조정 버튼 클릭 테스트")
    @Test
    fun countButtonTest() {
        onView(withId(R.id.btn_plus))
            .perform(click())

        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText("2")))

        onView(withId(R.id.btn_minus))
            .perform(click())

        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText("1")))
    }

    @DisplayName("화면이 회전 되어도 선택된 날짜와 시간 유지")
    @Test
    fun selectedDateTimeSaveTest() {
        // given
        onView(withId(R.id.sp_date)).perform(click())
        onData(anything()).atPosition(0).perform(click())
        onView(withId(R.id.sp_time)).perform(click())
        onData(anything()).atPosition(0).perform(click())

        // when
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        // then
        onView(withId(R.id.sp_date)).check(matches(withSpinnerText(FIRST_DATE)))
        onView(withId(R.id.sp_time)).check(matches(withSpinnerText(FIRST_TIME)))
    }

    @After
    fun tearDown() {
        scenario.close()
    }
}

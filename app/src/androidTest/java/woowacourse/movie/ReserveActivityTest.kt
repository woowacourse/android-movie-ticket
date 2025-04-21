package woowacourse.movie

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
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.fixture.createMovie

class ReserveActivityTest {
    private lateinit var intent: Intent
    private val movie = createMovie("해리포터")
    private lateinit var scenario: ActivityScenario<ReserveActivity>

    @Before
    fun setUp() {
        intent =
            Intent(
                ApplicationProvider.getApplicationContext(),
                ReserveActivity::class.java,
            ).apply {
                putExtra("movie", movie)
            }

        scenario = ActivityScenario.launch(intent)
    }

    @DisplayName("타이틀 글자 표시 테스트")
    @Test
    fun titleTest() {
        onView(withId(R.id.tv_title))
            .check(matches(withText("해리포터")))
    }

    @DisplayName("상영일 글자 표시 테스트")
    @Test
    fun screeningDateTest() {
        onView(withId(R.id.tv_screening_date))
            .check(matches(withText("2025.04.30 ~ 2025.05.04")))
    }

    @DisplayName("러닝타임 글자 표시 테스트")
    @Test
    fun runningTimeTest() {
        onView(withId(R.id.tv_running_time))
            .check(matches(withText("152분")))
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
        onView(withId(R.id.sp_date)).check(matches(withSpinnerText("2025-04-30")))
        onView(withId(R.id.sp_time)).check(matches(withSpinnerText("10:00")))
    }
}

package woowacourse.movie

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.fixture.FAKE_CONTEXT
import woowacourse.movie.fixture.HARRY_POTTER_01
import woowacourse.movie.view.movie.model.toUiModel
import woowacourse.movie.view.reservation.MovieReservationActivity
import java.time.LocalDate

@Suppress("ktlint:standard:function-naming")
class MovieReservationActivityTest {
    private lateinit var intent: Intent
    private lateinit var scenario: ActivityScenario<MovieReservationActivity>

    @get:Rule
    val activityRule = ActivityScenarioRule(MovieReservationActivity::class.java)

    @Before
    fun setUp() {
        intent =
            Intent(FAKE_CONTEXT, MovieReservationActivity::class.java).apply {
                putExtra("extra_movie", HARRY_POTTER_01.toUiModel())
            }
        scenario = ActivityScenario.launch(intent)
    }

    @Test
    fun 선택한_영화의_제목이_표시된다() {
        onView(withId(R.id.movie_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun 선택한_영화의_상영일이_표시된다() {
        onView(withId(R.id.screening_date)).check(matches(withText("상영일: 2025.04.01 ~ 2025.04.30")))
    }

    @Test
    fun 선택한_영화의_러닝타임이_표시된다() {
        onView(withId(R.id.running_time)).check(matches(withText("러닝타임: 152분")))
    }

    @Test
    fun 증가_버튼을_클릭하면_인원수가_1_증가한다() {
        // given
        onView(withId(R.id.ticket_count)).check(matches(withText("1")))

        // when
        onView(withId(R.id.increment_button)).perform(click())

        // then
        onView(withId(R.id.ticket_count)).check(matches(withText("2")))
    }

    @Test
    fun 감소_버튼을_클릭하면_인원수가_1_감소한다() {
        // given
        onView(withId(R.id.increment_button)).perform(click())
        onView(withId(R.id.ticket_count)).check(matches(withText("2")))

        // when
        onView(withId(R.id.decrement_button)).perform(click())

        // then
        onView(withId(R.id.ticket_count)).check(matches(withText("1")))
    }

    @Test
    fun 화면을_회전해도_입력한_정보가_유지된다() {
        onView(withId(R.id.increment_button)).perform(click())
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        onView(withId(R.id.ticket_count)).check(matches(withText("2")))
    }

    @Test
    fun 선택한_날짜가_Spinner에_표시된다() {
        // given
        onView(withId(R.id.date_spinner)).perform(click())

        // when
        onData(
            allOf(
                `is`(instanceOf(LocalDate::class.java)),
                `is`(LocalDate.of(2025, 5, 5)),
            ),
        ).perform(click())

        // then
        onView(withId(R.id.date_spinner)).check(matches(withSpinnerText("2025-05-05")))
    }
}

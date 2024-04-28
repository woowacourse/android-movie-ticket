package woowacourse.movie.presentation.seat

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_ID
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_RESERVATION_COUNT
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_SCREEN_DATE_TIME_ID

@RunWith(AndroidJUnit4::class)
class MovieSeatActivityTest {
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieSeatActivity::class.java,
        ).apply {
            putExtra(EXTRA_MOVIE_ID, 0L)
            putExtra(EXTRA_MOVIE_SCREEN_DATE_TIME_ID, 0L)
            putExtra(EXTRA_MOVIE_RESERVATION_COUNT, 1)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieSeatActivity>(intent)

    @Test
    fun `선택된_영화의_제목이_표시된다`() {
        onView(withId(R.id.seatMovieTitle))
            .check(matches(withText("타이타닉")))
    }

    @Test
    fun `S_클래스_좌석을_선택하면_15000원이_반영된다`() {
        onView(withText("E1")).perform(click())
        onView(withId(R.id.seatMoviePrice)).check(
            matches(withText("15,000")),
        )
    }

    @Test
    fun `A_클래스_좌석을_선택하면_12000원이_반영된다`() {
        onView(withText("D1")).perform(click())
        onView(withId(R.id.seatMoviePrice)).check(
            matches(withText("12,000")),
        )
    }

    @Test
    fun `B_클래스_좌석을_선택하면_12000원이_반영된다`() {
        onView(withText("A1")).perform(click())
        onView(withId(R.id.seatMoviePrice)).check(
            matches(withText("10,000")),
        )
    }

    @Test
    fun `클릭된_좌석과_가격_정보는_화면이_회전되어도_유지된다`() {
        onView(withText("E1")).perform(click())
        onView(withId(R.id.seatMoviePrice)).check(
            matches(withText("15,000")),
        )
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        onView(withId(R.id.seatMoviePrice)).check(
            matches(withText("15,000")),
        )
    }

    @Test
    fun `좌석을_모두_선택한_뒤_예매완료를_누르면_다이얼로그가_표시된다`() {
        onView(withText("E1")).perform(click())
        onView(withId(R.id.seatMoviePrice)).check(
            matches(withText("15,000")),
        )
        onView(withId(R.id.seatCompleteBtn)).perform(click())

        onView(withText(R.string.reservation_dialog_title)).check(matches(isDisplayed()))
    }

    @Test
    fun `좌석을_모두_선택하지_않고_예매완료를_누르면_다이얼로그가_표시되지_않는다`() {
        onView(withId(R.id.seatCompleteBtn)).perform(click())
        onView(withText("예매 확인")).check(doesNotExist())
    }
}

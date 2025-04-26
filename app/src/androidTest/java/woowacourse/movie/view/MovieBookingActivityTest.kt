package woowacourse.movie.view

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.MovieFixture
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import woowacourse.movie.R

@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieBookingActivityTest {
    private lateinit var scenario: ActivityScenario<MovieBookingActivity>

    @Before
    fun setUp() {
        val fakeContext = ApplicationProvider.getApplicationContext<Context>()
        val intent =
            Intent(
                fakeContext,
                MovieBookingActivity::class.java,
            ).apply {
                putExtra("movie", MovieFixture.MOVIE)
            }
        scenario = ActivityScenario.launch(intent)
    }

    @Test
    fun 영화정보가_올바르게_표시된다() {
        onView(withText(MovieFixture.HARRY_POTTER_TITLE)).check(matches(isDisplayed()))
        onView(withText(MovieFixture.HARRY_POTTER_DATE)).check(matches(isDisplayed()))
        onView(withText(MovieFixture.HARRY_POTTER_RUNNING_TIME)).check(matches(isDisplayed()))
    }

    @Test
    fun 인원수_버튼을_누르면_수정된다() {
        onView(withId(R.id.member_count))
            .check(matches(withText("1")))

        onView(withId(R.id.plus_member_count)).perform(ViewActions.click())

        onView(withId(R.id.member_count))
            .check(matches(withText("2")))

        onView(withId(R.id.minus_member_count)).perform(ViewActions.click())

        onView(withId(R.id.member_count))
            .check(matches(withText("1")))
    }

    @Test
    fun 예약_완료_버튼을_누르면_확인_다이얼로그가_나온다() {
        onView(withId(R.id.booking_complete_button)).perform(ViewActions.click())

        onView(withText(R.string.confirm_reservation_message))
            .check(matches(isDisplayed()))
    }
}



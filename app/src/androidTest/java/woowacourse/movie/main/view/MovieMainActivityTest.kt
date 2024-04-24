package woowacourse.movie.main.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R

@RunWith(AndroidJUnit4::class)
class MovieMainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieMainActivity::class.java)

    @Test
    fun `영화_목록이_화면에_표시된다`() {
        onView(withId(R.id.mainList)).check(matches(isDisplayed()))
    }

    @Test
    fun `영화_아이템에_썸네일_이미지가_표시된다`() {
        onView(withId(R.id.movieThumbnail)).check(matches(isDisplayed()))
    }

    @Test
    fun `영화_아이템에_영화_제목이_표시된다`() {
        onView(withId(R.id.movieTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun `영화_아이템에_영화_상영일이_표시된다`() {
        onView(withId(R.id.movieStartDate)).check(matches(isDisplayed()))
    }

    @Test
    fun `영화_아이템에_영화_러닝타임이_표시된다`() {
        onView(withId(R.id.movieRunningTime)).check(matches(isDisplayed()))
    }

    @Test
    fun `지금_예매_버튼을_클릭하면_영화_상세_페이지로_이동한다`() {
        onView(withId((R.id.movieReservationBtn))).perform(click())
        onView(withId(R.id.detailActivity)).check(matches(isDisplayed()))
    }
}

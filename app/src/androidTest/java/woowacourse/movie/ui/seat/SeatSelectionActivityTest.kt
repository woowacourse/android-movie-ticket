package woowacourse.movie.ui.seat

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotSelected
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.model.PeopleCountModel
import woowacourse.movie.ui.moviedetail.MovieDetailActivity
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
internal class SeatSelectionActivityTest {
    private val movieTitle = "테스트"
    private val movieTime = LocalDateTime.of(2023, 4, 22, 17, 0)
    private val peopleCount = PeopleCountModel(2)

    private val intent = Intent(
        ApplicationProvider.getApplicationContext(),
        SeatSelectionActivity::class.java
    ).apply {
        putExtra(MovieDetailActivity.KEY_TITLE, movieTitle)
        putExtra(MovieDetailActivity.KEY_TIME, movieTime)
        putExtra(MovieDetailActivity.KEY_PEOPLE_COUNT, peopleCount)
    }

    @get:Rule
    internal val activityRule = ActivityScenarioRule<SeatSelectionActivity>(intent)

    @Test
    fun 영화_제목이_보인다() {
        onView(withId(R.id.seat_movie_title))
            .check(matches(withText("테스트")))
    }

    @Test
    fun 아무_좌석도_선택하지_않으면_초기_가격으로_0원이_보인다() {
        onView(withId(R.id.seat_price))
            .check(matches(withText("0원")))
    }

    @Test
    fun E열_좌석을_선택하면_가격이_12000원이_된다() {
        onView(withText("E1"))
            .perform(click())

        onView(withId(R.id.seat_price))
            .check(matches(withText("12000원")))
    }

    @Test
    fun 빈_좌석을_선택하면_좌석이_선택된다() {
        onView(withText("E1"))
            // given
            .check(matches(isNotSelected()))
            // when
            .perform(click())
            // then
            .check(matches(isSelected()))
    }

    @Test
    fun 선택된_좌석을_재선택하면_선택이_해제된다() {
        onView(withText("E1"))
            // given
            .perform(click())
            .check(matches(isSelected()))
            // when
            .perform(click())
            // then
            .check(matches(isNotSelected()))
    }

    @Test
    fun 좌석_2개를_선택하면_확인_버튼이_활성화된다() {
        onView(withText("E1"))
            .perform(click())
        onView(withText("D1"))
            .perform(click())

        onView(withId(R.id.seat_confirm_button))
            .check(matches(isEnabled()))
    }

    @Test
    fun 선택된_좌석_2개_중_하나를_재선택하면_확인_버튼이_다시_비활성화된다() {
        onView(withText("E1"))
            .perform(click())
        onView(withText("D1"))
            .perform(click())
        onView(withId(R.id.seat_confirm_button))
            .check(matches(isEnabled()))

        onView(withText("E1"))
            .perform(click())
            .check(matches(isNotSelected()))

        onView(withId(R.id.seat_confirm_button))
            .check(matches(isNotEnabled()))
    }
}

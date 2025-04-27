package woowacourse.movie

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.BackgroundColorMatcher.withBackgroundColor
import woowacourse.movie.activity.reservation.ReservationSeatActivity
import woowacourse.movie.fixture.ActivityFixture

@RunWith(AndroidJUnit4::class)
@Suppress("FunctionName")
class ReservationSeatActivityTest {
    val intent =
        ReservationSeatActivity.newIntent(
            ApplicationProvider.getApplicationContext<Context>(),
            ActivityFixture.reservationDto,
        )

    @get:Rule
    val activityRule = ActivityScenarioRule<ReservationSeatActivity>(intent)

    @Test
    fun 다이얼로그의_바깥_쪽을_터치해도_사라지지_않는다() {
        // given
        onView(withText("A1"))
            .perform(click())
        onView(withId(R.id.submit))
            .perform(click())

        // when
        onView(isRoot())
            .perform(click())

        // then
        onView((withText(R.string.complete_dialog_title)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 다이얼로그에서_예매완료를_선택하면_예매_내역_화면으로_이동한다() {
        // given
        onView(withText("A1"))
            .perform(click())
        onView(withId(R.id.submit))
            .perform(click())

        onView(withText(R.string.complete_dialog_title)).check(matches(isDisplayed()))

        // when
        onView(withText(R.string.complete_dialog_positive_button))
            .perform(click())

        // then
        onView(withId(R.id.booking_success))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 선택_완료_버튼을_누르면_다이얼로그가_표시된다() {
        onView(withText("A1"))
            .perform(click())
        onView(withId(R.id.submit))
            .perform(click())

        onView(withText(R.string.complete_dialog_title))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 좌석을_선택하면_배경색이_바뀐다() {
        onView(withText("A1"))
            .perform(click())
            .check(matches(withBackgroundColor(R.color.seat_selected)))
    }

    @Test
    fun 좌석을_재선택하면_배경색이_원래_색으로_돌아운다() {
        onView(withText("A1"))
            .perform(click())
            .perform(click())
            .check(matches(withBackgroundColor(R.color.white)))
    }

    @Test
    fun 좌석을_선택하면_하단에_선택한_좌석_수를_반영한_최종_가격이_표시된다() {
        onView(withText("A1"))
            .perform(click())
        onView(withText("C1"))
            .perform(click())

        onView(withId(R.id.total))
            .check(matches(withText("27,000원")))
    }

    @Test
    fun 좌석은_예매_창에서_선택한_인원만큼_선택할_수_있다() {
        onView(withText("A1"))
            .perform(click())
        onView(withText("A2"))
            .perform(click())
        onView(withText("A3"))
            .perform(click())
            .check(matches(withBackgroundColor(R.color.white)))
    }

    @Test
    fun 모든_인원의_자리를_선택하지_않으면_예매할_수_없다() {
        onView(withText("A1"))
            .perform(click())

        onView(withId(R.id.submit))
            .check(matches(not(isEnabled())))
    }
}

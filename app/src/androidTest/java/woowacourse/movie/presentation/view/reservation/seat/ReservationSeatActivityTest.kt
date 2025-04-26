package woowacourse.movie.presentation.view.reservation.seat

import android.content.pm.ActivityInfo
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.presentation.fixture.fakeContext
import woowacourse.movie.presentation.model.ReservationInfoUiModel
import java.time.LocalDateTime

class ReservationSeatActivityTest {
    private lateinit var scenario: ActivityScenario<ReservationSeatActivity>
    private val fakeReservationInfo =
        ReservationInfoUiModel(
            "해리 포터와 마법사의 돌",
            LocalDateTime.of(2025, 4, 1, 11, 0),
            3,
            listOf(),
        )

    @Before
    fun setUp() {
        val intent = ReservationSeatActivity.newIntent(fakeContext, fakeReservationInfo)
        scenario = ActivityScenario.launch(intent)
    }

    @Test
    fun `영화_이름을_보여준다`() {
        onView(withId(R.id.tv_seats_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `선택_상태가_아닌_좌석을_선택하면_노란색으로_변경된다`() {
        onView(withText("A1")).check(matches(hasBackgroundColor(R.color.white)))

        onView(withText("A1")).perform(click())

        onView(withText("A1")).check(matches(hasBackgroundColor(R.color.yellow_fa)))
    }

    @Test
    fun `선택_상태인_좌석을_선택하면_하얀색으로_변경된다`() {
        onView(withText("A1")).perform(click())
        onView(withText("A1")).check(matches(hasBackgroundColor(R.color.yellow_fa)))

        onView(withText("A1")).perform(click())

        onView(withText("A1")).check(matches(hasBackgroundColor(R.color.white)))
    }

    @Test
    fun `좌석을_추가하면_선택_좌석의_총_가격을_보여준다`() {
        onView(withId(R.id.tv_seats_total_price)).check(matches(withText("0원")))

        onView(withText("A1")).perform(click()) // 좌석 추가
        onView(withText("A2")).perform(click()) // 좌석 추가
        onView(withText("A2")).perform(click()) // 좌석 제거

        onView(withId(R.id.tv_seats_total_price)).check(matches(withText("10,000원")))
    }

    @Test
    fun `선택_좌석_수를_충족하면_버튼이_활성화된다`() {
        onView(withId(R.id.btn_confirm))
            .check(matches(isNotEnabled()))

        onView(withText("A1")).perform(click())
        onView(withText("A2")).perform(click())
        onView(withText("A3")).perform(click())

        onView(withId(R.id.btn_confirm))
            .check(matches(isEnabled()))
    }

    @Test
    fun `확인_버튼을_누르면_예매_확인_다이얼로그가_노출된다`() {
        onView(withText("A1")).perform(click())
        onView(withText("A2")).perform(click())
        onView(withText("A3")).perform(click())
        onView(withId(R.id.btn_confirm)).perform(click())

        onView(withText("예매 확인")).check(matches(isDisplayed()))
    }

    @Test
    fun `예매_확인_다이얼로그_밖_영역을_터치해도_닫히지_않는다`() {
        onView(withText("A1")).perform(click())
        onView(withText("A2")).perform(click())
        onView(withText("A3")).perform(click())
        onView(withId(R.id.btn_confirm)).perform(click())

        pressBack()

        onView(withText("예매 확인")).check(matches(isDisplayed()))
    }

    @Test
    fun `화면을_회전해도_데이터가_유지된다`() {
        onView(withText("A1")).perform(click())
        onView(withText("A2")).perform(click())
        onView(withId(R.id.tv_seats_total_price)).check(matches(withText("20,000원")))

        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withText("A1")).check(matches(hasBackgroundColor(R.color.yellow_fa)))
        onView(withText("A2")).check(matches(hasBackgroundColor(R.color.yellow_fa)))

        onView(withId(R.id.tv_seats_total_price)).check(matches(withText("20,000원")))
    }

    private fun hasBackgroundColor(
        @ColorRes colorResId: Int,
    ): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has background color: $colorResId")
            }

            override fun matchesSafely(view: View): Boolean {
                val expectedColor = ContextCompat.getColor(view.context, colorResId)
                val background = view.background
                if (background is ColorDrawable) {
                    return background.color == expectedColor
                }
                return false
            }
        }
    }
}

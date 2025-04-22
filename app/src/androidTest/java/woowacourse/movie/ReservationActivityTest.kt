package woowacourse.movie

import android.content.Context
import android.content.pm.ActivityInfo
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.activity.ReservationActivity
import woowacourse.movie.fixture.AndroidTestFixture
import woowacourse.movie.global.ServiceLocator
import woowacourse.movie.global.newIntent
import java.time.LocalDate
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
@Suppress("FunctionName")
class ReservationActivityTest {
    init {
        ServiceLocator.today = LocalDate.of(2025, 4, 4)
        ServiceLocator.now = LocalDateTime.of(2025, 4, 4, 14, 0, 0)
    }

    val movie = AndroidTestFixture.movie1

    val intent =
        ApplicationProvider.getApplicationContext<Context>()
            .newIntent<ReservationActivity>(
                listOf(
                    ReservationActivity.MOVIE_KEY to movie,
                ),
            )

    @get:Rule
    val activityRule = ActivityScenarioRule<ReservationActivity>(intent)

    @Test
    fun 영화_예매_화면이_표시된다() {
        onView(withId(R.id.booking))
            .check(matches(isDisplayed()))
        onView(withText("해리포터와 마법사의 돌"))
            .check(matches(isDisplayed()))
        onView(withText("상영일: 2025.04.03 ~ 2025.04.05"))
            .check(matches(isDisplayed()))
        onView(withText("러닝타임: 125분"))
            .check(matches(isDisplayed()))
        onView(withId(R.id.movie_poster))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 각_영화의_상영일은_각자의_범위를_갖는다() {
        // given
        onView(withId(R.id.date_picker_actions))
            .perform(click())

        listOf(
            "04.03",
            "04.04",
            "04.05",
        ).forEachIndexed { idx, date ->
            onData(anything())
                .atPosition(idx)
                .check(matches(withText(date)))
        }
    }

    @Test
    fun 주말에는_오전_9시부터_두_시간_간격으로_상영한다() {
        // 2025년 4월 5일은 주말입니다
        // given
        onView(withId(R.id.date_picker_actions))
            .perform(click())

        // when
        onView(withText("04.05"))
            .perform(click())

        // then
        onView(withId(R.id.time_picker_actions))
            .perform(click())

        listOf(
            "09:00",
            "11:00",
            "13:00",
            "15:00",
            "17:00",
            "19:00",
            "21:00",
            "23:00",
        ).forEachIndexed { idx, time ->
            onData(anything())
                .atPosition(idx)
                .check(matches(withText(time)))
        }
    }

    @Test
    fun 평일에는_오전_10시부터_두_시간_간격으로_상영한다() {
        // 2025년 4월 3일은 평일입니다
        // given
        onView(withId(R.id.date_picker_actions))
            .perform(click())

        // when
        onView(withText("04.03"))
            .perform(click())

        // then
        onView(withId(R.id.time_picker_actions))
            .perform(click())

        listOf(
            "10:00",
            "12:00",
            "14:00",
            "16:00",
            "18:00",
            "20:00",
            "22:00",
            "00:00",
        ).forEachIndexed { idx, time ->
            onData(anything())
                .atPosition(idx)
                .check(matches(withText(time)))
        }
    }

    @Test
    fun 날짜와_시간은_현재_시간으로부터_가장_가까운_시간을_기본값으로_한다() {
        onView(withText("04.04"))
            .check(matches(isDisplayed()))
        onView(withText("15:00"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 플러스_버튼으로_인원_수를_1명_늘릴_수_있다() {
        onView(withText("+"))
            .perform(click())
        onView(withId(R.id.count))
            .check(matches(withText("2")))
    }

    @Test
    fun 마이너스_버튼으로_인원_수를_1명_줄일_수_있다() {
        // given
        onView(withText("+"))
            .perform(click())

        onView(withText("-"))
            .perform(click())
        onView(withId(R.id.count))
            .check(matches(withText("1")))
    }

    @Test
    fun 한_명_일때_인원_수를_줄일_수_없다() {
        onView(withText("-"))
            .perform(click())
        onView(withId(R.id.count))
            .check(matches(withText("1")))
    }

    @Test
    fun 예매_완료_버튼을_누르면_다이얼로그가_표시된다() {
        onView(withText("예매 완료"))
            .perform(click())

        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
    }

    @Test
    fun 다이얼로그의_바깥_쪽을_터치해도_사라지지_않는다() {
        // given
        onView(withText("선택 완료"))
            .perform(click())

        // when
        onView(isRoot())
            .perform(click())

        // then
        onView(withText("정말 예매하시겠습니까?"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 다이얼로그에서_예매완료를_선택하면_예매_내역_화면으로_이동한다() {
        // given
        onView(withText("선택 완료"))
            .perform(click())
        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))

        // when
        onView(withText("예매 완료"))
            .perform(click())

        // then
        onView(withId(R.id.booking_success))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 화면을_회전시켜도_정보가_유지된다() {
        // given
        onView(withText("+"))
            .perform(click())
        onView(withId(R.id.date_picker_actions))
            .perform(click())
        onView(withText("04.05"))
            .perform(click())
        onView(withId(R.id.time_picker_actions))
            .perform(click())
        onView(withText("15:00"))
            .perform(click())

        // when
        activityRule.scenario.onActivity { activity ->
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        }

        // then
        onView(withId(R.id.count))
            .check(matches(withText("2")))
        onView(withText("04.05"))
            .check(matches(isDisplayed()))
        onView(withText("15:00"))
            .check(matches(isDisplayed()))
    }
}

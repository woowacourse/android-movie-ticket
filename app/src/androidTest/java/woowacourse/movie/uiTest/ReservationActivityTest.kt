package woowacourse.movie.uiTest

import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import woowacourse.movie.R
import woowacourse.movie.activity.ReservationActivity
import woowacourse.movie.domain.Date
import woowacourse.movie.domain.Movie
import woowacourse.movie.uiTest.fixture.fakeContext
import java.time.LocalDate

class ReservationActivityTest {
    private lateinit var scenario: ActivityScenario<ReservationActivity>
    private lateinit var testName: String

    @get:Rule
    val nameRule = TestName()

    private val movie =
        Movie(
            R.drawable.harry,
            "해리 포터와 마법사의 돌",
            Date(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 25)),
            152,
        )

    @Before
    fun setUp() {
        testName = nameRule.methodName
        if (testName == "`null값이_Intent된_경우_ErrorDialog를_띄운다`") return
        val intent = ReservationActivity.newIntent(fakeContext, movie)
        scenario = ActivityScenario.launch<ReservationActivity>(intent)
    }

    @Test
    fun `선택한_아이템의_영화_제목을_보여준다`() {
        onView(withId(R.id.tv_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `선택한_아이템의_영화_상영_기간을_보여준다`() {
        onView(withId(R.id.tv_movie_date))
            .check(matches(withText("상영일: 2025.4.1 ~ 2025.4.25")))
    }

    @Test
    fun `선택한_아이템의_영화_상영_시간을_보여준다`() {
        onView(withId(R.id.tv_movie_time))
            .check(matches(withText("러닝타임: 152분")))
    }

    @Test
    fun `선택한_아이템의_예매_인원의_초기값은_1이다`() {
        onView(withId(R.id.tv_personnel))
            .check(matches(withText("1")))
    }

    @Test
    fun `예매_인원수가_플러스_버튼_두번_누르고_마이너스_버튼을_한번_누르면_2가_된다`() {
        onView(withId(R.id.btn_plus_button))
            .perform(click())
            .perform(click())

        onView(withId(R.id.btn_minus_button))
            .perform(click())

        onView(withId(R.id.tv_personnel))
            .check(matches(withText("2")))
    }

    @Test
    fun `선택_완료_버튼을_누르면_다이얼로그가_노출된다`() {
        onView(withId(R.id.btn_reservation))
            .perform(click())

        onView(withText("예매 확인")).check(matches(isDisplayed()))
        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
        onView(withText("예매 완료")).check(matches(isDisplayed()))
        onView(withText("취소")).check(matches(isDisplayed()))
    }

    @Test
    fun `다이얼로그_밖_영역을_터치해도_닫히지_않는다`() {
        onView(withId(R.id.btn_reservation))
            .perform(click())

        pressBack()

        onView(withText("예매 확인")).check(matches(isDisplayed()))
        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
        onView(withText("예매 완료")).check(matches(isDisplayed()))
        onView(withText("취소")).check(matches(isDisplayed()))
    }

    @Test
    fun `화면을_회전해도_데이터가_유지된다`() {
        onView(withId(R.id.btn_plus_button))
            .perform(click())
            .perform(click())

        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.tv_personnel))
            .check(matches(withText("3")))
    }

    @Test
    fun `화면을_회전해도_플러스_버튼을_클릭할_수_있다`() {
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.root_layout_reservation))
            .perform(swipeUp())

        onView(withId(R.id.btn_plus_button))
            .perform(click())
            .perform(click())

        onView(withId(R.id.tv_personnel))
            .check(matches(withText("3")))
    }

    @Test
    fun `화면을_회전해도_예매버튼을_클릭할_수_있다`() {
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.root_layout_reservation))
            .perform(swipeUp())

        onView(withId(R.id.btn_reservation))
            .perform(click())

        onView(withText("예매 확인")).check(matches(isDisplayed()))
        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
        onView(withText("예매 완료")).check(matches(isDisplayed()))
        onView(withText("취소")).check(matches(isDisplayed()))
    }

    @Test
    fun `null값이_Intent된_경우_ErrorDialog를_띄운다`() {
        val intent = ReservationActivity.newIntent(fakeContext, null)
        ActivityScenario.launch<ReservationActivity>(intent)

        onView(withText("오류가 생겼습니다.")).check(matches(isDisplayed()))
        onView(withText("잘못된 접근입니다.")).check(matches(isDisplayed()))
        onView(withText("확인")).check(matches(isDisplayed()))
    }
}

package woowacourse.movie.ui.detail

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R

@RunWith(AndroidJUnit4::class)
class ScreenDetailActivityTest {
    @get:Rule
    val activityRule: ActivityScenarioRule<ScreenDetailActivity> =
        ActivityScenarioRule<ScreenDetailActivity>(
            Intent(ApplicationProvider.getApplicationContext(), ScreenDetailActivity::class.java).apply {
                putExtra("screenId", 1)
            },
        )

    private val plusBtn: ViewInteraction = onView(withId(R.id.btn_plus))
    private val minusBtn: ViewInteraction = onView(withId(R.id.btn_minus))

    @Test
    fun `카운트가_10_미만일_때_플러스_버튼_눌러을_때_증가`() {
        // when
        plusBtn.perform(click())

        // then
        onView(withId(R.id.tv_count))
            .check(matches(withText("2")))
    }

    @Test
    fun `카운트가_1_초과일_때_마이너스_버튼_눌러을_때_감소`() {
        // given - 카운트가 2일 때
        plusBtn.perform(click())

        // when
        minusBtn.perform(click())

        // then
        onView(withId(R.id.tv_count))
            .check(matches(withText("1")))
    }

    @Test
    fun `카운트가_1일_때_마이너스_버튼_누르면_감소하지_않는다`() {
        // when
        minusBtn.perform(click())

        // then
        onView(withId(R.id.tv_count))
            .check(matches(withText("1")))
    }

    @Test
    fun `카운트가_10일_때_마이너스_버튼_누르면_증가하지_않는다`() {
        // given - 카운트가 1 -> 10 일 때
        plusBtn.perform(click())
        plusBtn.perform(click())
        plusBtn.perform(click())
        plusBtn.perform(click())
        plusBtn.perform(click())
        plusBtn.perform(click())
        plusBtn.perform(click())
        plusBtn.perform(click())
        plusBtn.perform(click())

        // when
        plusBtn.perform(click())

        // then
        onView(withId(R.id.tv_count))
            .check(matches(withText("10")))
    }


    @Test
    fun `카운트가_2일_떄_화면을_가로로_회전해도_카운트가_유지된다`() {
        val activityScenario = activityRule.scenario
        plusBtn.perform(click())

        activityScenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.tv_count)).check(matches(withText("2")))
    }
}

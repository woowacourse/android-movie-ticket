package woowacourse.movie.presentation.ui.detail

import android.content.Intent
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
import woowacourse.movie.presentation.utils.repeatClick

@RunWith(AndroidJUnit4::class)
class DetailActivityTest {
    @get:Rule
    val activityRule: ActivityScenarioRule<DetailActivity> =
        ActivityScenarioRule<DetailActivity>(
            Intent(ApplicationProvider.getApplicationContext(), DetailActivity::class.java).apply {
                putExtra("screenId", 1)
            },
        )

    private val plusBtn: ViewInteraction = onView(withId(R.id.btn_plus))
    private val minusBtn: ViewInteraction = onView(withId(R.id.btn_minus))

    @Test
    fun `초기_티켓이_카운트가_1일_때_플러스_버튼을_누르면_티켓의_수가_2가_된다`() {
        // when
        plusBtn.perform(click())

        // then
        onView(withId(R.id.tv_count)).check(matches(withText("2")))
    }

    @Test
    fun `초기_티켓이_카운트가_2일_때_마이너스_버튼을_누르면_티켓의_수가_1이_된다`() {
        // given - 카운트가 2일 때
        plusBtn.perform(click())

        // when
        minusBtn.perform(click())

        // then
        onView(withId(R.id.tv_count)).check(matches(withText("1")))
    }

    @Test
    fun `초기_티켓이_카운트가_1일_때_마이너스_버튼을_누르면_티켓의_수가_변화지_않는다`() {
        // when
        minusBtn.perform(click())

        // then
        onView(withId(R.id.tv_count)).check(matches(withText("1")))
    }

    @Test
    fun `초기_티켓이_카운트가_10일_때_플러스_버튼을_누르면_티켓의_수가_변화지_않는다`() {
        // given - 카운트가 1 -> 10 일 때
        plusBtn.repeatClick(9)

        // when
        plusBtn.perform(click())

        // then
        onView(withId(R.id.tv_count)).check(matches(withText("10")))
    }
}

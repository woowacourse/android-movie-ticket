package woowacourse.movie

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.activity.MainActivity
import woowacourse.movie.fixture.AndroidTestFixture
import woowacourse.movie.global.ServiceLocator

@RunWith(AndroidJUnit4::class)
@Suppress("FunctionName")
class MainActivityTest {
    init {
        ServiceLocator.movies = AndroidTestFixture.movies1
    }

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun 영화_정보가_표시된다() {
        onView(withId(R.id.main))
            .check(matches(isDisplayed()))

        val data =
            onData(anything())
                .inAdapterView(withId(R.id.movies))
                .atPosition(0)
        listOf(
            withText("해리포터와 마법사의 돌"),
            withText("상영일: 2025.04.01 ~ 2025.04.25"),
            withText("러닝타임: 125분"),
            withText("지금 예매"),
            withId(R.id.movie_poster),
        ).forEach {
            data.onChildView(it)
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun 지금_예매_버튼을_누르면_예매_화면으로_이동한다() {
        onView(withText("지금 예매"))
            .perform(click())
        onView(withId(R.id.booking))
            .check(matches(isDisplayed()))
    }
}

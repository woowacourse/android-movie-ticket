package woowacourse.movie.java

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import io.kotest.matchers.string.match
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.MainActivity
import woowacourse.movie.R

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun 빼기_버튼을_누르면_값이_감소한다() {
        onView(withId(R.id.sub))
            .perform(click())

        onView(withId(R.id.count))
            .check(matches(withText("-1")))
    }
}

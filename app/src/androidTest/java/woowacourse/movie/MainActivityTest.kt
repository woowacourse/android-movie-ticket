package woowacourse.movie

import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.anything
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var item: DataInteraction

    @Before
    fun setUp() {
        item =
            onData(anything())
                .inAdapterView(withId(R.id.lv_movies))
                .atPosition(20)
    }

    @Test
    fun shouldDisplayTitleText() {
        item.onChildView(withId(R.id.tv_title)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayScreeningDateText() {
        item.onChildView(withId(R.id.tv_title)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayRunningTimeText() {
        item.onChildView(withId(R.id.tv_running_time)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayPosterImage() {
        item.onChildView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayReserveButton() {
        item.onChildView(withId(R.id.btn_reserve)).check(matches(isDisplayed()))
    }
}

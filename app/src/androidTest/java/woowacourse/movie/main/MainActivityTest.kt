package woowacourse.movie.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.ui.main.MainActivity

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val targetPosition = 1

    @Test
    fun shouldDisplayTitleText() {
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(targetPosition))
            .check(matches(hasDescendant(withText("해리포터와 마법사의 돌"))))
    }

    @Test
    fun shouldDisplayScreeningDateText() {
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(targetPosition))
            .check(matches(hasDescendant(withText("2025.04.15 ~ 2025.05.23"))))
    }

    @Test
    fun shouldDisplayRunningTimeText() {
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(targetPosition))
            .check(matches(hasDescendant(withText("152분"))))
    }

    @Test
    fun shouldDisplayPosterImage() {
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(targetPosition))
            .check(matches(hasDescendant(withId(R.id.iv_poster))))
    }

    @Test
    fun shouldDisplayReserveButton() {
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(targetPosition))
            .check(matches(hasDescendant(withText("지금 예매"))))
    }
}

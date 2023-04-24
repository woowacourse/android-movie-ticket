package woowacourse.movie

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.view.movielist.MovieListActivity

class MovieListActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @get:Rule
    val intentsTestRule = IntentsTestRule(MovieListActivity::class.java)

    @Test
    fun 영화를_클릭하면_아무런_일도_일어나지_않는다() {
        // given: 영화목록과 광고들이 화면에 나오고

        // when: 영화 리스트를 클릭했을 때
        onView(withId(R.id.movie_recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                    MOVIE_POSITION,
                    click()
                )
            )

        // then: 아무런 일도 일어나지 않는다.
        onView(withId(R.id.movie_recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun 광고배너를_클릭하면_해당_웹페이지가_띄워진다() {
        // given: 영화목록과 광고들이 화면에 나오고

        // when: 광고를 클릭했을 때
        onView(withId(R.id.movie_recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(AD_POSITION, click()))

        // then: 웹페이지를 띄운다.
        intended(hasAction(Intent.ACTION_VIEW))
    }

    companion object {
        private const val MOVIE_POSITION = 0
        private const val AD_POSITION = 3
    }
}

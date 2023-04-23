package woowacourse.movie.ui.movielist

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents.init
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.release
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.ui.moviedetail.MovieDetailActivity

@RunWith(AndroidJUnit4::class)
internal class MainActivityTest {
    @get:Rule
    internal val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        init()
    }

    @Test
    fun 첫번째_아이템을_선택하면_MovieDetailActivity로_이동한다() {
        onView(withId(R.id.main_movie_list))
            .perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        intended(hasComponent(MovieDetailActivity::class.java.name))
    }

    @Test
    fun 광고_아이템을_선택하면_ACTION_VIEW_intent가_실행된다() {
        onView(withId(R.id.main_movie_list))
            .perform(actionOnItemAtPosition<ViewHolder>(3, click()))

        intended(hasAction(Intent.ACTION_VIEW))
    }

    @After
    fun tearDown() {
        release()
    }
}

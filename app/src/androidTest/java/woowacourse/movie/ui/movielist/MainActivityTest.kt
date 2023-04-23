package woowacourse.movie.ui.movielist

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents.init
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.release
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.ui.moviedetail.MovieDetailActivity
import woowacourse.movie.ui.movielist.adapter.MovieListViewType

@RunWith(AndroidJUnit4::class)
internal class MainActivityTest {
    @get:Rule
    internal val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        init()
    }

    @After
    fun tearDown() {
        release()
    }

    @Test
    fun 첫번째_아이템을_선택하면_MovieDetailActivity로_이동한다() {
        onView(withId(R.id.main_movie_list))
            .perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        intended(hasComponent(MovieDetailActivity::class.java.name))
    }

    @Test
    fun 광고_아이템을_선택하면_URI를_가진_ACTION_VIEW_intent가_실행된다() {
        onView(withId(R.id.main_movie_list))
            .perform(actionOnItemAtPosition<ViewHolder>(3, click()))

        intended(
            allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(Uri.parse("https://github.com/woowacourse"))
            )
        )
    }

    @Test
    fun 영화가_세_번_노출될_때마다_광고가_한_번_노출된다() {
        activityRule.scenario.onActivity { activity ->
            val recyclerView = activity.findViewById<RecyclerView>(R.id.main_movie_list)

            val actual = recyclerView.adapter?.getItemViewType(3)
            val expected = MovieListViewType.AD.value

            assertEquals(expected, actual)
        }
    }
}

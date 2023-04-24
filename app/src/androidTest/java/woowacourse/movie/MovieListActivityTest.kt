package woowacourse.movie

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.activity.moviedetail.MovieDetailActivity
import woowacourse.movie.activity.movielist.CustomViewHolder
import woowacourse.movie.activity.movielist.ListViewType
import woowacourse.movie.activity.movielist.MovieListActivity
import woowacourse.movie.model.toPresentation
import woowacourse.movie.util.DummyData
import java.time.format.DateTimeFormatter

class MovieListActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    val dummyDataToPresentation = DummyData.movies.map { it.toPresentation(R.drawable.img) }

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun 아이템_3개마다_광고뷰를_띄운다() {
        activityRule.scenario.onActivity { activity ->
            val recyclerView = activity.findViewById<RecyclerView>(R.id.recycler_view)

            val expected = recyclerView.adapter?.getItemViewType(3)
            val actual = ListViewType.AD_VIEWTYPE.ordinal

            assertEquals(expected, actual)
        }
    }

    @Test
    fun 광고를_제외한_모든_아이템은_영화_정보_뷰를_띄운다() {
        activityRule.scenario.onActivity { activity ->
            val recyclerView = activity.findViewById<RecyclerView>(R.id.recycler_view)

            for (position in 0 until 3) {
                val expected = recyclerView.adapter?.getItemViewType(position)
                val actual = ListViewType.NORMAL_VIEWTYPE.ordinal

                assertEquals(expected, actual)
            }
        }
    }

    @Test
    fun 광고는_이미지를_띄운다() {
        onView(withId(R.id.recycler_view))
            .perform(scrollToPosition<CustomViewHolder>(3))
            .check(matches(hasDescendant(withId(R.id.img_ad))))
    }

    @Test
    fun 광고_이외_아이템은_영화_정보를_띄운다() {
        val movie = dummyDataToPresentation[0]
        val formatter = DateTimeFormatter.ofPattern("YYYY.M.d")
        val dateTime =
            "상영일: ${formatter.format(movie.startDate)} ~ ${formatter.format(movie.endDate)}"
        val runningTime = "러닝타임: ${movie.runningTime}분"
        onView(withId(R.id.recycler_view))
            .perform(scrollToPosition<CustomViewHolder>(0))
            .check(matches(hasDescendant(withText(movie.title))))
        onView(withId(R.id.recycler_view)).check(matches(hasDescendant(withText(dateTime))))
        onView(withId(R.id.recycler_view)).check(matches(hasDescendant(withText(runningTime))))
    }

    @Test
    fun 예매_버튼을_누르면_상세_액티비티를_띄운다() {
        onView(withId(R.id.recycler_view))
            .perform(
                actionOnItemAtPosition<CustomViewHolder>(
                    0,
                    clickChildViewWithId(R.id.btn_reserve),
                ),
            )
        intended(hasComponent(MovieDetailActivity::class.java.name))
    }

    fun clickChildViewWithId(id: Int): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController?, view: View) {
                val v: View = view.findViewById(id)
                v.performClick()
            }
        }
    }
}

package woowacourse.movie

import android.view.View
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
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.activity.moviedetail.MovieDetailActivity
import woowacourse.movie.activity.movielist.MovieListActivity
import woowacourse.movie.activity.movielist.MovieListAdapter
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
    fun 아이템_3개마다_광고가_포함된_아이템을_띄운다() {
        DummyData.movies.forEachIndexed { index, _ ->
            if (index != 0 && index % 3 == 0) {
                onView(withId(R.id.recycler_view))
                    .perform(scrollToPosition<MovieListAdapter.ViewHolder>(index))
                onView(withId(R.id.img_ad)).check(matches(isDisplayed()))
            }
        }
    }

    @Test
    fun 모든_아이템은_제목을_띄운다() {
        dummyDataToPresentation.forEachIndexed { index, movie ->
            onView(withId(R.id.recycler_view))
                .perform(scrollToPosition<MovieListAdapter.ViewHolder>(index))
                .check(matches(hasDescendant(withText(movie.title))))
        }
    }

    @Test
    fun 모든_아이템은_상영_기간을_띄운다() {
        val formatter = DateTimeFormatter.ofPattern("YYYY.M.d")
        dummyDataToPresentation.forEachIndexed { index, movie ->
            val text =
                "상영일: ${formatter.format(movie.startDate)} ~ ${formatter.format(movie.endDate)}"
            onView(withId(R.id.recycler_view))
                .perform(scrollToPosition<MovieListAdapter.ViewHolder>(index))
                .check(matches(hasDescendant(withText(text))))
        }
    }

    @Test
    fun 모든_아이템은_러닝_타임을_띄운다() {
        dummyDataToPresentation.forEachIndexed { index, movie ->
            val text = "러닝타임: ${movie.runningTime}분"
            onView(withId(R.id.recycler_view))
                .perform(scrollToPosition<MovieListAdapter.ViewHolder>(index))
                .check(matches(hasDescendant(withText(text))))
        }
    }

    @Test
    fun 예매_버튼을_누르면_상세_액티비티를_띄운다() {
        onView(withId(R.id.recycler_view))
            .perform(
                actionOnItemAtPosition<MovieListAdapter.ViewHolder>(
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

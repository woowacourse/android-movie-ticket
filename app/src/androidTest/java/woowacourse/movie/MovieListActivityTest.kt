package woowacourse.movie

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.MovieListActivityTest.ClickViewAction.clickViewWithId
import woowacourse.movie.movieList.MovieListActivity
import woowacourse.movie.movieReservation.ReservationActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieListActivityTest {
    object ClickViewAction {
        fun clickViewWithId(id: Int): ViewAction = object : ViewAction {
            override fun getDescription(): String = "id를 기반으로 아이템을 클릭한다."

            override fun getConstraints(): Matcher<View>? = null

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id)
                v.performClick()
            }
        }
    }

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MovieListActivity::class.java)

    @Test
    fun 영화를_선택하면_다음_인원_수_입력_창으로_이동한다() {
        onView(withId(R.id.movie_list))
            .check(matches(isDisplayed()))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(0, clickViewWithId(R.id.movie_reservation_button)),
            )
        intended(hasComponent(ReservationActivity::class.java.name))
    }

    @Test
    fun 영화가_3개_나올_때마다_광고가_하나_나온다() {
        activityScenarioRule.scenario.onActivity { activity ->
            val recyclerView = activity.findViewById<RecyclerView>(R.id.movie_list)

            val expected = recyclerView.adapter?.getItemViewType(3)
            val actual = TYPE_AD

            assertEquals(expected, actual)
        }
    }

    companion object {
        private const val TYPE_AD = 2
    }
}

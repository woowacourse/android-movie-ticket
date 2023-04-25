package woowacourse.movie

import android.app.Instrumentation
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.activity.MovieDetailActivity
import woowacourse.movie.activity.MovieListActivity
import woowacourse.movie.adapter.viewholder.AdViewHolder
import woowacourse.movie.adapter.viewholder.MovieViewHolder

class MovieListActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @get:Rule
    val intentsTestRule = IntentsTestRule(MovieListActivity::class.java)

    @Test
    fun 리사이클러뷰에_세_개의_영화가_연달아_나온다() {
        activityRule.scenario.onActivity { activity ->

            // given: 첫 번째 ~ 세 번째 position에 대해 반복한다
            (0..2).fold(0) { position, _ ->

                // when: position 번째 아이템의 뷰타입을 가져오면
                val viewType =
                    activity.findViewById<RecyclerView>(R.id.recycler_view).adapter!!.getItemViewType(
                        position
                    )

                // then: 영화 아이템 뷰 타입이다.
                assertEquals(viewType, MOVIE_ITEM_VIEW_TYPE)
                position.plus(1)
            }
        }
    }

    @Test
    fun 광고는_4의_배수_위치에_나온다() {
        activityRule.scenario.onActivity { activity ->

            // when: 4 번째 아이템의 뷰타입을 가져오면
            val viewType =
                activity.findViewById<RecyclerView>(R.id.recycler_view).adapter!!.getItemViewType(3)

            // then: 광고 아이템 뷰 타입이다.
            assertEquals(viewType, AD_ITEM_VIEW_TYPE)
        }
    }

    @Test
    fun 광고는_4의_배수_위치가_아니면_나오지_않는다() {
        activityRule.scenario.onActivity { activity ->

            // when: 7 번째 아이템의 뷰타입을 가져오면
            val viewType =
                activity.findViewById<RecyclerView>(R.id.recycler_view).adapter!!.getItemViewType(6)

            // then: 광고 아이템 뷰 타입이다.
            assertNotEquals(viewType, AD_ITEM_VIEW_TYPE)
        }
    }

    @Test
    fun 영화_아이템의_예매_버튼을_클릭하면_영화_예매_화면으로_이동한다() {

        // when: 영화 아이템의 예매 버튼을 클릭하면
        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MovieViewHolder>(
                0,
                MyViewAction.clickChildViewWithId(R.id.btn_ticketing)
            )
        )

        // then: 영화 예매 화면으로 이동한다
        intended(hasComponent(MovieDetailActivity::class.java.name))
    }

    @Test
    fun 광고_아이템을_클릭하면_해당_링크로_연결된다() {

        // given: 광고 아이템 클릭 시 Intent의 액션은 ACTION_VIEW 이며 해당 링크로 이동한다
        val url = "https://techcourse.woowahan.com/"
        val expectedIntent = allOf(hasAction(Intent.ACTION_VIEW), hasData(url))
        intending(expectedIntent).respondWith(Instrumentation.ActivityResult(0, null))

        // when: 광고 아이템을 클릭하면
        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AdViewHolder>(
                3,
                click()
            )
        )

        // then: 해당 링크로 이동한다
        intended(expectedIntent)
    }

    object MyViewAction {
        fun clickChildViewWithId(id: Int): ViewAction {
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

    companion object {
        private const val MOVIE_ITEM_VIEW_TYPE = 0
        private const val AD_ITEM_VIEW_TYPE = 1
    }
}

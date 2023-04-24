package woowacourse.movie.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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
import woowacourse.movie.R
import woowacourse.movie.ui.adv.AdvDetailActivity
import woowacourse.movie.ui.reservation.MovieDetailActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun `광고_아이템_레이아웃이_세번째_인덱스에_위치한다`() {
        for (index in 3..33 step 4) {
            var viewType = 0
            activityRule.scenario.onActivity {
                viewType =
                    it.findViewById<RecyclerView>(R.id.rv_main).adapter!!.getItemViewType(index)
            }
            assertEquals(R.layout.adv_item_layout, viewType)
        }
    }

    @Test
    fun 영화_아이템_레이아웃이_세번째_인덱스마다_위치한다() {
        var viewType = 0
        activityRule.scenario.onActivity {
            viewType =
                it.findViewById<RecyclerView>(R.id.rv_main).adapter!!.getItemViewType(2)
        }
        assertEquals(R.layout.movie_item_layout, viewType)
    }

    @Test
    fun `8번째_인덱스에_위치한_영화의_제목이_일치한다`() {
        onView(withId(R.id.rv_main))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8)
            )
        onView(withText("더 퍼스트 슬램덩크 6"))
    }

    @Test
    fun `광고아이템을_클릭하면_광고화면으로_이동한다`() {
        onView(withId(R.id.rv_main))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    3,
                    click()
                )
            )

        onView(withId(R.id.adv_detail_img))
            .check(matches(isDisplayed()))

        intended(hasComponent(AdvDetailActivity::class.java.name))
    }

    @Test
    fun `영화_아이템을_클릭하면_영화_예매화면으로_이동한다`() {
        onView(withId(R.id.rv_main))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    object : ViewAction {
                        override fun getDescription(): String = "지금 예매 버튼을 클릭한다"

                        override fun getConstraints(): Matcher<View>? = null

                        override fun perform(uiController: UiController, view: View) {
                            val v = view.findViewById<View>(R.id.reservation)
                            v.performClick()
                        }
                    }
                )
            )

        intended(hasComponent(MovieDetailActivity::class.java.name))
    }
}

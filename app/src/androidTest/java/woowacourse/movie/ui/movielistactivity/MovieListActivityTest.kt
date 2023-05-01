package woowacourse.movie.ui.movielistactivity

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R

@RunWith(AndroidJUnit4::class)
class MovieListActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun `리사이클러뷰가_화면에_보이는지_검사`() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `리스트_0번째에_0번째_영화요소가_들어있는지_검사`() {
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<ViewHolder>(0))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withText("해리 포터와 마법사의 돌 0"), isDisplayed()
                        )
                    )
                )
            )
    }

    @Test
    fun `리스트_4번째에_광고가_표시되는지_검사`() {
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<ViewHolder>(3))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withId(R.id.iv_advertisement), isDisplayed()
                        )
                    )
                )
            )
    }

    @Test
    fun `광고를_클릭하면_우아한_페이지로_이동한다`() {
        val url = "https://www.woowahan.com/"

        onView(withId(R.id.rv_movie))
            .perform(
                actionOnItemAtPosition<ViewHolder>(3, click())
            )

        intended(hasAction(Intent.ACTION_VIEW))
        intended(hasData(Uri.parse(url)))
    }

    @Test
    fun `지금예매_버튼을_누르면_영화정보_화면으로_이동한다`() {
        onView(withId(R.id.rv_movie))
            .perform(
                actionOnItemAtPosition<ViewHolder>(
                    0,
                    object : ViewAction {
                        override fun getDescription(): String = "지금 예매 버튼을 클릭한다"

                        override fun getConstraints() = null

                        override fun perform(uiController: UiController, view: View) {
                            val v = view.findViewById<View>(R.id.btn_booking)
                            v.performClick()
                        }
                    }
                )
            )
    }
}

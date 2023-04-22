package woowacourse.movie.ui.movielist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.woowacourse.movie.domain.Movie
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.ui.ticketing.TicketingActivity

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
    fun `영화_목록_1번째_영화의_제목이_보인다`() {
        onView(withId(R.id.recycler_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withText(Movie.provideDummy()[0].title),
                            isDisplayed(),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun `영화_목록_3번째에_광고가_보인다`() {
        onView(withId(R.id.recycler_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withId(
                                R.id.iv_poster
                            ),
                            isDisplayed()
                        )
                    )
                )
            )
    }

    @Test
    fun `지금_예매_버튼을_누르면_티켓팅_화면으로_이동한다`() {
        onView(withId(R.id.recycler_movies))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    object : ViewAction {
                        override fun getDescription(): String = "id를 기반으로 아이템을 클릭한다."

                        override fun getConstraints(): Matcher<View>? = null

                        override fun perform(uiController: UiController?, view: View) {
                            val v = view.findViewById<View>(R.id.btn_book)
                            v.performClick()
                        }
                    }
                )
            )
        intended(hasComponent(TicketingActivity::class.java.name))
    }
}

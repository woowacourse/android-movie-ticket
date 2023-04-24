package woowacourse.movie.ui.movielist

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.ui.ticketing.TicketingActivity
import woowacourse.movie.ui.util.checkDisplayed
import woowacourse.movie.ui.util.performAction
import woowacourse.movie.ui.util.performScrollTo

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
            .performScrollTo(0)
            .checkDisplayed("해리 포터와 마법사의 돌")
    }

    @Test
    fun `영화_목록_3번째에_광고가_보인다`() {
        onView(withId(R.id.recycler_movies))
            .performScrollTo(0)
            .checkDisplayed(R.id.iv_poster)
    }

    @Test
    fun `지금_예매_버튼을_누르면_티켓팅_화면으로_이동한다`() {
        onView(withId(R.id.recycler_movies))
            .performAction(
                0,
                onItemIDClick(R.id.btn_book)
            )
        intended(hasComponent(TicketingActivity::class.java.name))
    }

    companion object {
        fun onItemIDClick(id: Int): ViewAction =
            object : ViewAction {
                override fun getDescription(): String = "id를 기반으로 아이템을 클릭한다."

                override fun getConstraints(): Matcher<View>? = null

                override fun perform(uiController: UiController?, view: View) {
                    val v = view.findViewById<View>(id)
                    v.performClick()
                }
            }
    }
}

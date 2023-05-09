package woowacourse.movie

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents.init
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.release
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.ui.booking.BookingActivity
import woowacourse.movie.ui.home.HomeActivity

class HomeActivityTest {
    @get:Rule
    internal val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        init()
    }

    @After
    fun tearDown() {
        release()
    }

    @Test
    fun 첫_번째_아이템_영화를_클릭하면_예매하기_뷰로_이동한다() {
        // given : 영화목록
        val movieList = onView(withId(R.id.rv_main_movie_list))

        // when : 첫 번째 아이템을 눌렀을 때
        movieList.perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                FIRST,
                ViewActions.click(),
            ),
        )

        // then : 예매 하기 뷰로 이동 한다
        intended(hasComponent(BookingActivity::class.java.name))
    }

    @Test
    fun 광고를_클릭하면_광고_페이지로_이동한다() {
        // given : 영화목록
        val movieList = onView(withId(R.id.rv_main_movie_list))

        // when : 광고를 눌렀을 때
        movieList.perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                ADVERTISEMENT,
                ViewActions.click(),
            ),
        )

        // then : 정해진 페이지로 이동한다
        intended(
            allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(Uri.parse(URL)),
            ),
        )
    }

    companion object {
        private const val FIRST = 0
        private const val ADVERTISEMENT = 3
        private const val URL = "https://github.com/woowacourse"
    }
}

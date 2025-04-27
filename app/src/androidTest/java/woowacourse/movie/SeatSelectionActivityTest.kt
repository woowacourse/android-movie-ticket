package woowacourse.movie

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.fixtures.fakeContext
import woowacourse.movie.fixtures.theater
import woowacourse.movie.fixtures.ticket
import woowacourse.movie.view.seatSelection.SeatSelectionActivity

class SeatSelectionActivityTest {
    private lateinit var intent: Intent
    private lateinit var scenario: ActivityScenario<SeatSelectionActivity>

    @get:Rule
    val activityRule = ActivityScenarioRule(SeatSelectionActivity::class.java)

    @Before
    fun setUp() {
        intent =
            Intent(fakeContext, SeatSelectionActivity::class.java).apply {
                putExtra("ticket", ticket)
                putExtra("seats", theater)
            }
        scenario = ActivityScenario.launch(intent)
    }

    @Test
    @DisplayName("선택한 영화의 제목이 표시된다")
    fun displaySelectedMovieTitleTest() {
        onView(withId(R.id.movie_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    @DisplayName("S, A, B등급 좌석을 하나씩 선택하면 총 액수로 37,000원이 표시된다")
    fun displayTotalPriceTest() {
        onView(withId(R.id.a1)).perform(click())
        onView(withId(R.id.c1)).perform(click())
        onView(withId(R.id.e1)).perform(click())
        onView(withId(R.id.total_price)).check(matches(withText("37,000원")))
    }

    @Test
    @DisplayName("화면이 회전돼도 입력한 정보가 유지된다")
    fun retainInformationOnScreenRotateTest() {
        onView(withId(R.id.a1)).perform(click())
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        onView(withId(R.id.total_price)).check(matches(withText("10,000원")))
    }
}

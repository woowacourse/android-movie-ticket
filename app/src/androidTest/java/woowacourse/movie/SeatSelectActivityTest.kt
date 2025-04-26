package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.fixture.FAKE_CONTEXT
import woowacourse.movie.fixture.INITIAL_TICKET
import woowacourse.movie.view.reservation.model.toUiModel
import woowacourse.movie.view.seat.SeatSelectActivity

@Suppress("ktlint:standard:function-naming")
class SeatSelectActivityTest {
    private lateinit var intent: Intent

    @get:Rule
    val activityRule = ActivityScenarioRule(SeatSelectActivity::class.java)

    @Before
    fun setUp() {
        intent =
            Intent(FAKE_CONTEXT, SeatSelectActivity::class.java).apply {
                putExtra("extra_ticket", INITIAL_TICKET.toUiModel())
            }
        ActivityScenario.launch<SeatSelectActivity>(intent)
    }

    @Test
    fun 선택한_영화의_제목이_표시된다() {
        onView(withId(R.id.movie_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun 선택한_좌석의_총_금액이_표시된다() {
        // given
        onView(withId(R.id.total_price)).check(matches(withText("0원")))

        // when
        onView(withText("B3")).perform(click())
        onView(withText("D2")).perform(click())

        // then
        onView(withId(R.id.total_price)).check(matches(withText("25,000원")))
    }

    @Test
    fun 선택된_인원_수만큼_좌석을_선택하면_확인_버튼을_활성화한다() {
        // given
        onView(withId(R.id.confirm_button)).check(matches(isNotEnabled()))

        // when
        onView(withText("B3")).perform(click())
        onView(withText("D2")).perform(click())

        // then
        onView(withId(R.id.confirm_button)).check(matches(isEnabled()))
    }
}

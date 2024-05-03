package woowacourse.movie.seat

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.model.Ticket

class SeatSelectActivityTest {
    private lateinit var seats: Sequence<TextView>

    @get:Rule
    val activityRule =
        ActivityScenarioRule<SeatSelectActivity>(
            Intent(
                ApplicationProvider.getApplicationContext(),
                SeatSelectActivity::class.java,
            ).apply {
                putExtra("movieId", 0)
                putExtra("ticket", Ticket(1, "2001.11.14", "10:00"))
            },
        )

    @Before
    fun setUp() {
        activityRule.scenario.onActivity {
            seats =
                it.findViewById<GridLayout>(R.id.grid_layout_seat_select)
                    .children
                    .filterIsInstance<TextView>()
        }
    }

    @Test
    fun `영화_정보_해리_포터와_마법사의_돌가_보여진다`() {
        onView(withId(R.id.text_view_seat_select_movie_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `영화_좌석이_20개가_보여진다`() {
        onView(withId(R.id.grid_layout_seat_select)).check(matches(hasChildCount(20)))
    }

    @Test
    fun `좌석을_선택하면_배경색이_노란색으로_변경된다`() {
        activityRule.scenario.onActivity {
            val item = seats.first()
            item.performClick()

            assertEquals((item.background as ColorDrawable).color, ContextCompat.getColor(it, R.color.yellow))
        }
    }

    @Test
    fun `좌석_선택_취소하면_하얀색으로_돌아간다`() {
        activityRule.scenario.onActivity {
            val item = seats.first()
            repeat(2) { item.performClick() }

            assertEquals((item.background as ColorDrawable).color, ContextCompat.getColor(it, R.color.white))
        }
    }

    @Test
    fun `확인버튼_선택_시_예매_확인_다이얼로그가_나타난다`() {
        onView(withText("A1")).perform(click())

        onView(withId(R.id.button_seat_select_confirm)).perform(click())
        onView(withText("예매 확인")).check(matches(isDisplayed()))
        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
    }
}

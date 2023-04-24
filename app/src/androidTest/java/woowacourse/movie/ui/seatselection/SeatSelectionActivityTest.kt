package woowacourse.movie.ui.seatselection

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.woowacourse.movie.domain.Movie
import com.woowacourse.movie.domain.seat.Rank
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.model.ReservationUI
import woowacourse.movie.model.TicketCountUI
import woowacourse.movie.model.TicketsUI
import woowacourse.movie.model.mapper.toMovieUI
import woowacourse.movie.ui.ticketingresult.TicketingResultActivity
import java.time.LocalDateTime

class SeatSelectionActivityTest {
    // 해리포터
    private val reservation = ReservationUI(
        Movie.provideDummy()[0].toMovieUI(),
        LocalDateTime.of(2023, 4, 21, 12, 0),
        TicketsUI(setOf()),
        TicketCountUI(2)
    )

    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            SeatSelectionActivity::class.java
        ).apply {
            putExtra(RESERVATION_KEY, reservation)
        }

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule<SeatSelectionActivity>(intent)
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun `이전_화면에서_예매한_영화_제목이_나타난다`() {
        onView(withId(R.id.tv_title))
            .check(matches(withText(reservation.movie.title)))
    }

    @Test
    fun `좌석_하나를_선택하면_선택한_좌석의_상태가_바뀐다`() {
        onView(withText("A1"))
            .perform(click())

        onView(withText("A1"))
            .check(matches(isSelected()))
    }

    @Test
    fun `좌석_하나를_선택하면_선택한_좌석의_가격으로_갱신된다`() {
        onView(withText("A1"))
            .perform(click())

        onView(withId(R.id.tv_price))
            .check(matches(withText(context.getString(R.string.movie_price, Rank.B.price))))
    }

    @Test
    fun `2장_예매할_때_두_좌석을_선택하면_확인_버튼이_활성화_된다`() {
        onView(withText("A1"))
            .perform(click())

        onView(withText("A2"))
            .perform(click())

        onView(withId(R.id.btn_ok))
            .check(matches(isEnabled()))
    }

    @Test
    fun `2장_예매할_때_선택한_좌석이_2개가_아니라면_확인_버튼이_비활성화_된다`() {
        onView(withText("A1"))
            .perform(click())

        onView(withText("A2"))
            .perform(click())

        onView(withText("A2"))
            .perform(click())

        onView(withId(R.id.btn_ok))
            .check(matches(isNotEnabled()))
    }

    @Test
    fun `확인_버튼을_누르면_다이얼로그가_보여진다`() {
        onView(withText("A1"))
            .perform(click())

        onView(withText("A2"))
            .perform(click())

        onView(withId(R.id.btn_ok))
            .perform(click())

        onView(withText(context.getString(R.string.dialog_reservation_title)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `다이얼로그_예매_완료_버튼을_누르면_예매_결과_화면으로_넘어간다`() {
        onView(withText("A1"))
            .perform(click())

        onView(withText("A2"))
            .perform(click())

        onView(withId(R.id.btn_ok))
            .perform(click())

        onView(withText(context.getString(R.string.dialog_reservation_positive_button)))
            .perform(click())

        Intents.intended(IntentMatchers.hasComponent(TicketingResultActivity::class.java.name))
    }

    companion object {
        private const val RESERVATION_KEY = "reservation"
    }
}

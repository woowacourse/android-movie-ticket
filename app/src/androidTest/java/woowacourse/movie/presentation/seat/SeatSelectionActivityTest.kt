package woowacourse.movie.presentation.seat

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.widget.TableLayout
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.domain.model.MovieDate
import woowacourse.movie.presentation.model.PendingMovieReservationModel
import woowacourse.movie.presentation.model.toMovieDateModel
import woowacourse.movie.presentation.reservation.MovieReservationPresenter
import woowacourse.movie.utils.findTextViewByTextInTableLayout

@RunWith(AndroidJUnit4::class)
class SeatSelectionActivityTest {
    @get:Rule
    val activityRule =
        ActivityScenarioRule<SeatSelectionActivity>(
            Intent(
                ApplicationProvider.getApplicationContext(),
                SeatSelectionActivity::class.java,
            ).apply {
                val pendingMovieReservationModel =
                    PendingMovieReservationModel(
                        title = "해리 포터와 마법사의 돌",
                        movieDate = MovieDate().toMovieDateModel(),
                        count = 2,
                    )
                putExtra(
                    MovieReservationPresenter.KEY_NAME_PENDING_RESERVATION,
                    pendingMovieReservationModel
                )
            },
        )

    @Test
    fun `현재_텍스트_확인`() {
        onView(withId(R.id.movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `좌석을_모두_선택_후_확인_가능_버튼이_활성화되어야_한다`() {
        activityRule.scenario.onActivity { context ->
            val tableView = context.findViewById<TableLayout>(R.id.seat_table)
            val confirmButton = context.findViewById<TextView>(R.id.confirm_button)
            val expected = (confirmButton.background as ColorDrawable).color
            tableView.findTextViewByTextInTableLayout("A1")?.performClick()
            tableView.findTextViewByTextInTableLayout("A2")?.performClick()
            val actual = (confirmButton.background as ColorDrawable).color
            assertNotEquals(expected, actual)
        }
    }

    @Test
    fun `선택되지_않은_좌석을_선택했을_때_선택한_좌석_자리의_색상이_초기_색상과_달라진다`() {
        activityRule.scenario.onActivity { context ->
            val tableView =
                context.findViewById<TableLayout>(R.id.seat_table)
            val textView = tableView.findTextViewByTextInTableLayout("A1")
            val expected = textView?.background
            textView?.performClick()
            val actual = textView?.background

            assertNotEquals(expected, actual)
        }
    }

    @Test
    fun `선택된_좌석을_선택했을_때_선택한_좌석_자리의_색상이_초기_색상으로_달라진다`() {
        activityRule.scenario.onActivity { context ->
            val tableView =
                context.findViewById<TableLayout>(R.id.seat_table)
            val textView = tableView.findTextViewByTextInTableLayout("A2")
            textView?.performClick()
            val expected = (textView?.background as ColorDrawable).color
            textView.performClick()
            val actual = (textView.background as ColorDrawable).color

            assertNotEquals(expected, actual)
        }
    }
}

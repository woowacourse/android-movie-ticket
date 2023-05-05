package woowacourse.movie.view.activities.seatselection

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.view.activities.seatselection.SeatSelectionActivity.Companion.SCREENING_DATE_TIME
import woowacourse.movie.view.activities.seatselection.SeatSelectionActivity.Companion.SCREENING_ID
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class SeatSelectionActivityTest {

    private val intent = Intent(ApplicationProvider.getApplicationContext(), SeatSelectionActivity::class.java).apply {
        putExtra(SCREENING_ID, 1L)
        putExtra(SCREENING_DATE_TIME, LocalDateTime.of(2024, 3, 1, 10, 0))
    }

    @get:Rule
    val rule = ActivityScenarioRule<SeatSelectionActivity>(intent)

    @Test
    fun 좌석_선택_액티비티가_실행되면_확인이라는_텍스트를_가진_버튼이_표시된다() {
        onView(withText("확인")).check(matches(isDisplayed()))
    }
}

package woowacourse.movie.view.activities.screeningdetail

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.view.activities.screeningdetail.ScreeningDetailActivity.Companion.SCREENING_ID

class ScreeningDetailActivityTest {

    private val intent = Intent(ApplicationProvider.getApplicationContext(), ScreeningDetailActivity::class.java).apply {
        putExtra(SCREENING_ID, 1L)
    }

    @get:Rule
    val rule = ActivityScenarioRule<ScreeningDetailActivity>(intent)

    @Test
    fun 상영_상세_액티비티가_실행되면좌석_선택이라는_텍스트를_가진_버튼이_표시된다() {
        onView(withText("좌석 선택")).check(matches(isDisplayed()))
    }
}

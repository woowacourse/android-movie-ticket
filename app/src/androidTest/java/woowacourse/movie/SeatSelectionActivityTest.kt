package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class SeatSelectionActivityTest {
    @get:Rule
    val activityScenarioRule =
        ActivityScenarioRule<SeatSelectionActivity>(
            Intent(ApplicationProvider.getApplicationContext(), SeatSelectionActivity::class.java).apply {
                putExtra("movie_id", 0L)
            },
        )

    @Test
    fun `영화_제목과_좌석_배치도가_올바르게_표출된다`() {
        onView(withId(R.id.tv_movie_title)).check(matches(withText("해리 포터와 마법사의 돌")))
        onView(withId(R.id.tl_screens)).check(matches(isDisplayed()))
    }

    @Test
    fun `특정_좌석을_선택하면_배경색이_선택_색상으로_변경된다`() {
    }

    @Test
    fun `특정_좌석_선택을_취소하면_미선택_색상으로_변경된다`() {
    }

    @Test
    fun `화면이_회전되어도_좌석_선택_정보가_유지된다`() {
    }

    @Test
    fun `인원수에_맞는_좌석_수만큼_선택되면_더_이상_다른_좌석이_선택되지_않는다`() {
    }

    @Test
    fun `인원수에_맞는_좌석_수만큼_선택되면_확인_버튼이_활성화된다`() {
    }

    @Test
    fun `확인_버튼_클릭_시_예약_확정_다이얼로그가_표출된다`() {
    }

    @Test
    fun `예약_확정_다이얼로그에서_취소_버튼_클릭_시_다이얼로그가_사라진다`() {
    }

    @Test
    fun `예약_확정_다이얼로그_이외의_영역_클릭_시에도_다이얼로그가_사라지지_않는다`() {
    }
}

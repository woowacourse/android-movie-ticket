package woowacourse.movie.feature.completed

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.feature.completed.ReservationCompletedActivity.Companion.TICKET_ID

@RunWith(AndroidJUnit4::class)
class ReservationCompletedActivityTest {
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            ReservationCompletedActivity::class.java,
        ).also { it.putExtra(TICKET_ID, 0L) }

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule<ReservationCompletedActivity>(intent)

    @Test
    fun `예약_완료_화면에_안내_메세지가_표시된다`() {
        Espresso.onView(ViewMatchers.withId(R.id.completed_message))
            .check(ViewAssertions.matches(ViewMatchers.withText("영화 상영 시작 시간 15분 전까지\n취소가 가능합니다")))
    }

    @Test
    fun `예매한_영화_제목이_표시된다`() {
        Espresso.onView(ViewMatchers.withId(R.id.completed_movie_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `예매_티켓_수량에_맞는_명_수가_표시된다`() {
        Espresso.onView(ViewMatchers.withId(R.id.completed_quantity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `예매_티켓_수량에_맞는_가격이_표시된다`() {
        Espresso.onView(ViewMatchers.withId(R.id.completed_price))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}

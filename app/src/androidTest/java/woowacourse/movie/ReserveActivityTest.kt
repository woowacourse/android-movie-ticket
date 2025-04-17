package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class ReserveActivityTest {
    private lateinit var intent: Intent
    private val movie = HARRY_POTTER_MOVIE

    @Before
    fun setUp() {
        intent =
            Intent(
                ApplicationProvider.getApplicationContext(),
                ReserveActivity::class.java,
            ).apply {
                putExtra("movie", movie)
            }

        ActivityScenario.launch<ReserveActivity>(intent)
    }

    @DisplayName("타이틀 글자 표시 테스트")
    @Test
    fun titleTest() {
        onView(withId(R.id.tv_title))
            .check(matches(withText(HARRY_POTTER_TITLE)))
    }

    @DisplayName("상영일 글자 표시 테스트")
    @Test
    fun screeningDateTest() {
        onView(withId(R.id.tv_screening_date))
            .check(matches(withText(HARRY_POTTER_FORMATTED_DATE)))
    }

    @DisplayName("러닝타임 글자 표시 테스트")
    @Test
    fun runningTimeTest() {
        onView(withId(R.id.tv_running_time))
            .check(matches(withText("${HARRY_POTTER_RUNNING_TIME}분")))
    }

    @DisplayName("예매 티켓 수 조정 버튼 클릭 테스트")
    @Test
    fun countButtonTest() {
        onView(withId(R.id.btn_plus))
            .perform(click())

        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText("2")))

        onView(withId(R.id.btn_minus))
            .perform(click())

        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText("1")))
    }
}

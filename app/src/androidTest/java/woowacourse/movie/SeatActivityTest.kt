package woowacourse.movie

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.domain.Points
import woowacourse.movie.fixture.MOVIE_NAME
import woowacourse.movie.fixture.createReservation
import woowacourse.movie.seat.SeatActivity

class SeatActivityTest {
    private lateinit var intent: Intent
    private val reservation = createReservation(MOVIE_NAME, Points())
    private lateinit var scenario: ActivityScenario<SeatActivity>

    @Before
    fun setUp() {
        val fakeActivity: Context = ApplicationProvider.getApplicationContext()
        intent =
            Intent(
                fakeActivity,
                SeatActivity::class.java,
            ).apply {
                putExtra(KeyIdentifiers.KEY_RESERVATION, reservation)
            }

        scenario = ActivityScenario.launch(intent)
    }

    @DisplayName("타이틀 글자 표시 테스트")
    @Test
    fun titleTest() {
        onView(withId(R.id.tv_title))
            .check(matches(withText(MOVIE_NAME)))
    }

    @DisplayName("좌석 클릭 시 가격 변경 테스트")
    @Test
    fun changePriceWhenClickButton() {
        // when
        onView(withText("A1"))
            .perform(click())

        // then
        onView(withId(R.id.tv_total_price))
            .check(matches(withText("10,000원")))

        // when
        onView(withText("A1"))
            .perform(click())

        // then
        onView(withId(R.id.tv_total_price))
            .check(matches(withText("0원")))
    }

    @DisplayName("좌석 선택 시 확인 버튼 활성화 테스트")
    @Test
    fun changeButtonEnabled() {
        onView(withId(R.id.btn_select))
            .check(matches(isNotEnabled()))

        // when
        onView(withText("A1"))
            .perform(click())
        onView(withText("A2"))
            .perform(click())
        onView(withText("A3"))
            .perform(click())

        // then
        onView(withId(R.id.btn_select))
            .check(matches(isEnabled()))
    }

    @After
    fun tearDown() {
        scenario.close()
    }
}

package woowacourse.movie.ui.seatSelect

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.data.MovieRepository
import woowacourse.movie.model.CountState
import woowacourse.movie.model.ReservationState
import woowacourse.movie.ui.seat.SeatSelectActivity
import woowacourse.movie.ui.util.checkMatches
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
@LargeTest
class SeatSelectActivityTest {

    // 더 퍼스트 슬램덩크 1
    private val movie = MovieRepository.allMovies()[1]
    private val dateTime = LocalDateTime.of(2023, 1, 4, 10, 0, 0)
    private val reservationState =
        ReservationState(movie, dateTime, CountState.of(2))

    private val intent = SeatSelectActivity.getIntent(
        ApplicationProvider.getApplicationContext(),
        reservationState
    )

    @get:Rule
    val activityRule = ActivityScenarioRule<SeatSelectActivity>(intent)

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
        onView(withId(R.id.reservation_title))
            .checkMatches(withText(reservationState.movieState.title))
    }

    // 커스텀 뷰인 SeatView의 isChosen 속성의 활성화 여부를 테스트하는 법을 아직 모르겠어서, 통합 테스트를 수행
    @Test
    fun `예약_개수만큼_좌석을_선택하면_확인_버튼이_활성화된다`() {
        onView(withText("확인"))
            .check(matches(not(isClickable())))

        onView(withText("A2"))
            .perform(click())
        onView(withText("A3"))
            .perform(click())

        onView(withText("확인"))
            .check(matches(isClickable()))
    }
}

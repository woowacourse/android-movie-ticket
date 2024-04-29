package woowacourse.movie.presentation.view

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.TableLayout
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.data.repository.MovieTicketRepositoryImpl
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.hasBackground
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import woowacourse.movie.presentation.ui.seat.SeatActivity

@RunWith(AndroidJUnit4::class)
class SeatActivityTest {
    private val testContext = ApplicationProvider.getApplicationContext<Context>()
    private val intent: Intent = seatActivityIntent(testContext, 1)
    private lateinit var seatBoard: TableLayout
    private lateinit var seatTable: List<Button>
    
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule<SeatActivity>(intent)
    
    private fun seatActivityIntent(
        context: Context,
        movieTicketId: Int,
    ): Intent =
        Intent(context, SeatActivity::class.java).apply {
            putExtra(EXTRA_MOVIE_TICKET_ID, movieTicketId)
        }
    
    @Before
    fun setup() {
        // given 테스트용 예매 정보 생성
        MovieTicketRepositoryImpl.createMovieTicket(TITLE, SCREENING_LOCAL_DATE_TIME, 1)
    }
    
    @Test
    fun `좌석_선택_화면이_나타난다`() {
        // then
        onView(withId(R.id.seat_activity)).check(matches(isDisplayed()))
    }
    
    @Test
    fun 좌석이_제대로_표시되는지_테스트() {
        onView(withId(R.id.seat_board_layout)).check(matches(isDisplayed()))
    }
    
    @Test
    fun 확인버튼이_초기에_비활성화되어있는지_테스트() {
        onView(withId(R.id.confirm_button)).check(matches(not(isEnabled())))
    }
    
    @Test
    fun 영화_제목_표시_테스트() {
        Log.d("얍",intent.getIntExtra(EXTRA_MOVIE_TICKET_ID, -1).toString(),)
        Log.d("얍",MovieTicketRepositoryImpl.getMovieTicket(1).toString())
        Log.d("얍", "${R.id.title}")
        onView(withId(R.id.title)).check(matches(withText(TITLE)))
    }
    
    @Test
    fun 좌석_선택시_색상_변경_테스트() {
        onView(withId(R.id.seat_board_layout)).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.seat_1), isDescendantOfA(withId(R.id.first_row)))).perform(click())
        // then
        onView(allOf(withId(R.id.seat_1), isDescendantOfA(withId(R.id.first_row))))
            .check(matches(hasBackground(R.drawable.selected_seat)))
    }
    
    @Test
    fun 필요한_좌석_모두_선택시_확인버튼_활성화_테스트() {
        // when
        val seatIds = arrayOf(R.id.first_row, R.id.seat_2)
        seatIds.forEach {
            onView(withId(it)).perform(click())
        }
        
        // then
        onView(withId(R.id.confirm_button)).check(matches(isEnabled()))
    }
    
    @Test
    fun 확인_버튼_클릭시_다이얼로그_표시_테스트() {
        // when
        val seatIds = arrayOf(R.id.seat_1, R.id.seat_2)
        seatIds.forEach {
            onView(withId(it)).perform(click())
        }
        onView(withId(R.id.confirm_button)).perform(click())
        
        // then
        onView(withText(R.string.reservation_confirm_title)).check(matches(isDisplayed()))
    }
    
    @Test
    fun 다이얼로그_취소_버튼_클릭시_다이얼로그_사라짐_테스트() {
        onView(withId(R.id.confirm_button)).perform(click())
        onView(withText(android.R.string.cancel)).perform(click())
        onView(withText(R.string.reservation_confirm_title)).check(doesNotExist())
    }
}

package woowacourse.movie.ui.movieselectseatactivity

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.core.IsNot.not
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.ui.moviebookingcheckactivity.MovieBookingCheckActivity
import woowacourse.movie.ui.movieselectseatactivity.MovieSelectSeatActivity.Companion.BOOKED_SCREENING_DATE_TIME
import woowacourse.movie.ui.movieselectseatactivity.MovieSelectSeatActivity.Companion.MOVIE_DATA
import woowacourse.movie.ui.movieselectseatactivity.MovieSelectSeatActivity.Companion.TICKET_COUNT
import java.time.LocalDate
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class MovieSelectSeatActivityTest {

    private val dummyMovieData = MovieUIModel(
        title = "해리포터",
        screeningStartDay = LocalDate.parse("2023-03-01"),
        screeningEndDay = LocalDate.parse("2023-03-31"),
        runningTime = 30,
        description = ""
    )

    // 무비데이,조조 할인 적용시간
    private val dummyIntentData =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieSelectSeatActivity::class.java
        ).apply {
            putExtra(MOVIE_DATA, dummyMovieData)
            putExtra(TICKET_COUNT, 3)
            putExtra(BOOKED_SCREENING_DATE_TIME, LocalDateTime.parse("2023-03-10T09:00"))
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieSelectSeatActivity>(dummyIntentData)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun `영화_제목이_표시된다`() {
        onView(withId(R.id.tv_movie_title)).check(matches(withText("해리포터")))
    }

    @Test
    fun `최초로_계산된_가격은_0이다`() {
        onView(withId(R.id.tv_movie_total_price)).check(matches(withText("0원")))
    }

    @Test
    fun `좌석이_선택된다면_isSelected_속성이_true_로_바뀐다`() {
        onView(withText("A1")).perform(ViewActions.click()).check((matches(isSelected())))
    }

    @Test
    fun `좌석이_선택된다면_가격표시_뷰의_변화가_일어난다`() {
        onView(withText("A1")).perform(ViewActions.click())
        onView(withId(R.id.tv_movie_total_price)).check((matches(not(withText("0원")))))
    }

    @Test
    fun `이미_선택된_좌석을_클릭하면_isSelected_속성이_false_로_바뀐다`() {
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("A1")).perform(ViewActions.click())
        onView(withId(R.id.tv_movie_total_price)).check(matches(isNotSelected()))
    }

    @Test
    fun `좌석을_티켓_갯수만큼_선택했다면_더이상_선택할수_없다`() {
        // when: 좌석을 3자리 선택했다.
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("B1")).perform(ViewActions.click())
        onView(withText("C1")).perform(ViewActions.click())

        // then: 3자리이후 좌석은 선택되지 않는다.
        onView(withText("D1")).perform(ViewActions.click()).check(matches(isNotSelected()))
    }

    @Test
    fun `좌석을_티켓_갯수만큼_선택했다면_확인_버튼이_활성화_된다`() {
        // when: 좌석을 3자리 선택했다.
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("B1")).perform(ViewActions.click())
        onView(withText("C1")).perform(ViewActions.click())

        // then: 버튼이 활성화 된다.
        onView(withId(R.id.btn_check)).check(matches(isClickable()))
    }

    @Test
    fun `확인_버튼이_활성화된_상태에서_좌석을_취소한다면_확인_버튼이_비활성화_된다`() {
        // when: 좌석을 3자리 선택했다 한자리 취소한다.
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("B1")).perform(ViewActions.click())
        onView(withText("C1")).perform(ViewActions.click())
        // then: 버튼이 비활성화 된다
        onView(withText("A1")).perform(ViewActions.click())
        onView(withId(R.id.btn_check)).check(matches(isNotClickable()))
    }

    @Test
    fun 확인_버튼을_누르면_다이어로그가_나온다() {
        // when:좌석을 티켓 수만큼 선택하고 확인버튼을 누른다.
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("B1")).perform(ViewActions.click())
        onView(withText("C1")).perform(ViewActions.click())
        onView(withId(R.id.btn_check)).perform(ViewActions.click())

        // then:다이얼로그가 뜬다
        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
    }

    @Test
    fun 다이어로그에서_확인을_누르면_결제화면으로_이동한다() {
        // when:좌석을 티켓 수만큼 선택하고 확인버튼을 누른 예매완료 버튼을 누른다.
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("B1")).perform(ViewActions.click())
        onView(withText("C1")).perform(ViewActions.click())
        onView(withId(R.id.btn_check)).perform(ViewActions.click())
        onView(withText("예매 완료")).perform(ViewActions.click())

        // then: 액티비티가 전환된다
        intended(hasComponent(MovieBookingCheckActivity::class.java.name))
    }
}

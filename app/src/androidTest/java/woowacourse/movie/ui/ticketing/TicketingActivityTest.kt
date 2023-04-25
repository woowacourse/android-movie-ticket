package woowacourse.movie.ui.ticketing

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.ui.movielist.data.MovieRepository
import woowacourse.movie.ui.seatselection.SeatSelectionActivity
import woowacourse.movie.ui.util.checkMatches
import woowacourse.movie.ui.util.performClick

class TicketingActivityTest {
    // 해리포터
    private val movie = MovieRepository.allMovies()[0]

    private val intent = TicketingActivity.getIntent(
        ApplicationProvider.getApplicationContext(),
        movie
    )

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule<TicketingActivity>(intent)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun `영화_제목이_보여진다`() {
        onView(withId(R.id.tv_title))
            .checkMatches(withText(movie.title))
    }

    @Test
    fun `영화_상영일이_보여진다`() {
        onView(withId(R.id.tv_date))
            .checkMatches(withText("상영일: 2023.04.01 ~ 2023.04.30"))
    }

    @Test
    fun `영화_줄거리가_보여진다`() {
        onView(withId(R.id.tv_introduce))
            .checkMatches(withText(movie.introduce))
    }

    @Test
    fun `plus_버튼을_누르면_티켓_개수가_1증가한다`() {
        onView(withId(R.id.btn_plus))
            .checkMatches(isDisplayed())
            .performClick()

        onView(withId(R.id.tv_ticket_count))
            .checkMatches(withText("2"))
    }

    @Test
    fun `좌석_선택_버튼을_누르면_좌석_선택_화면으로_넘어간다`() {
        onView(withId(R.id.btn_plus))
            .checkMatches(isDisplayed())
            .performClick()

        onView(withId(R.id.btn_ticketing))
            .checkMatches(isDisplayed())
            .performClick()

        Intents.intended(IntentMatchers.hasComponent(SeatSelectionActivity::class.java.name))
    }
}

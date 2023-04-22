package woowacourse.movie.ui.ticketing

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.woowacourse.movie.domain.Movie
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.model.mapper.toMovieUI
import woowacourse.movie.ui.seatselection.SeatSelectionActivity
import java.time.format.DateTimeFormatter

class TicketingActivityTest {
    // 해리포터
    private val movie = Movie.provideDummy()[0]
    private val intent =
        Intent(ApplicationProvider.getApplicationContext(), TicketingActivity::class.java).apply {
            putExtra(MOVIE_KEY, movie.toMovieUI())
        }

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule<TicketingActivity>(intent)
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

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
            .check(matches(withText(movie.title)))
    }

    @Test
    fun `영화_상영일이_보여진다`() {
        onView(withId(R.id.tv_date))
            .check(
                matches(
                    withText(
                        context.getString(
                            R.string.movie_release_date,
                            movie.startDate.format(
                                DateTimeFormatter.ofPattern(
                                    MOVIE_DATE_PATTERN
                                )
                            ),
                            movie.endDate.format(DateTimeFormatter.ofPattern(MOVIE_DATE_PATTERN))
                        )
                    )
                )
            )
    }

    @Test
    fun `영화_줄거리가_보여진다`() {
        onView(withId(R.id.tv_introduce))
            .check(
                matches(
                    withText(movie.introduce)
                )
            )
    }

    @Test
    fun `plus_버튼을_누르면_티켓_개수가_1증가한다`() {
        onView(withId(R.id.btn_plus))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.tv_ticket_count))
            .check(
                matches(
                    withText(
                        "1"
                    )
                )
            )
    }

    @Test
    fun `좌석_선택_버튼을_누르면_좌석_선택_화면으로_넘어간다`() {
        onView(withId(R.id.btn_plus))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.btn_ticketing))
            .check(matches(isDisplayed()))
            .perform(click())

        Intents.intended(IntentMatchers.hasComponent(SeatSelectionActivity::class.java.name))
    }

    companion object {
        internal const val MOVIE_DATE_PATTERN = "yyyy.MM.dd"
        internal const val MOVIE_KEY = "movie"
    }
}

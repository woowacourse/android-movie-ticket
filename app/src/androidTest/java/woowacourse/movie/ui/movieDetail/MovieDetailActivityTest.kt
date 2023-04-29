package woowacourse.movie.ui.movieDetail

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.data.MovieRepository
import woowacourse.movie.ui.reservation.MovieDetailActivity
import woowacourse.movie.ui.seat.SeatSelectActivity
import woowacourse.movie.ui.util.checkMatches

@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieDetailActivityTest {

    // 더 퍼스트 슬램덩크 1
    private val movie = MovieRepository.allMovies()[1]

    private val intent = MovieDetailActivity.getIntent(
        ApplicationProvider.getApplicationContext(),
        movie
    )

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieDetailActivity>(intent)

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
        onView(withId(R.id.detail_title))
            .checkMatches(withText(movie.title))
    }

    @Test
    fun `영화_상영일이_보여진다`() {
        onView(withId(R.id.detail_date))
            .checkMatches(withText("상영일: 2023.1.4 ~ 2023.2.23"))
    }

    @Test
    fun `영화_줄거리가_보여진다`() {
        onView(withId(R.id.description))
            .checkMatches(withText(movie.description))
    }

    @Test
    fun `plus_버튼을_누르면_티켓_개수가_1증가한다`() {
        onView(withId(R.id.plus))
            .checkMatches(isDisplayed())
            .perform(click())

        onView(withId(R.id.count))
            .checkMatches(withText("2"))
    }

    @Test
    fun `minus_버튼을_누르면_티켓_개수가_1감소한다`() {
        onView(withId(R.id.plus))
            .checkMatches(isDisplayed())
            .perform(click())

        onView(withId(R.id.minus))
            .checkMatches(isDisplayed())
            .perform(click())

        onView(withId(R.id.count))
            .checkMatches(withText("1"))
    }

    @Test
    fun `좌석_선택_화면으로_넘어간다`() {
        onView(withId(R.id.plus))
            .checkMatches(isDisplayed())
            .perform(click())

        onView(withId(R.id.reservation_confirm))
            .checkMatches(isDisplayed())
            .perform(click())

        Intents.intended(IntentMatchers.hasComponent(SeatSelectActivity::class.java.name))
    }
}

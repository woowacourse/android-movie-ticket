package woowacourse.movie.ui.complete

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.model.movie.MovieDate
import woowacourse.movie.model.movie.ReservationCount
import woowacourse.movie.model.movie.Ticket
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RunWith(AndroidJUnit4::class)
class MovieReservationCompleteActivityTest {
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val movieContent: MovieContent = MovieContentsImpl.find(0L)

    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieReservationCompleteActivity::class.java,
        ).run {
            putExtra(MovieReservationCompleteKey.ID, 0L)
            putExtra(MovieReservationCompleteKey.COUNT, RESERVATION_COUNT)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieReservationCompleteActivity>(intent)

    @Test
    fun `화면이_띄워지면_영화_제목이_보인다`() {
        onView(withId(R.id.title_text))
            .check(matches(isDisplayed()))
            .check(matches(withText(movieContent.title)))
    }

    @Test
    fun `화면이_띄워지면_상영일이_보인다`() {
        val screeningDate = dateFormatter(movieContent.screeningMovieDate)

        onView(withId(R.id.screening_date_text))
            .check(matches(isDisplayed()))
            .check(matches(withText(screeningDate)))
    }

    @Test
    fun `화면이_띄워지면_예매_인원이_1인_경우_예매_인원의_수가_보인다`() {
        onView(withId(R.id.reservation_count_text))
            .check(matches(isDisplayed()))
            .check(matches(withText("일반 ${RESERVATION_COUNT}명")))
    }

    @Test
    fun `화면이_띄워지면_예매_금액이_보인다`() {
        val reservationAmount = Ticket(ReservationCount()).amount()

        onView(withId(R.id.reservation_amount_text))
            .check(matches(isDisplayed()))
            .check(matches(withText("%,d원 (현장 결제)".format(reservationAmount))))
    }

    private fun dateFormatter(movieDate: MovieDate): String {
        val screeningDate = LocalDate.of(movieDate.year, movieDate.month, movieDate.day)
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        return screeningDate.format(formatter)
    }

    companion object {
        private const val RESERVATION_COUNT = 1

        @JvmStatic
        @BeforeClass
        fun setUp() {
            MovieContentsImpl.save(
                MovieContent(
                    "movie_poster",
                    "해리 포터와 마법사의 돌",
                    MovieDate(2024, 3, 1),
                    152,
                    "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, " +
                        "판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다. ",
                ),
            )
        }
    }
}

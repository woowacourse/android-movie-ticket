package woowacourse.movie.presentation.reservation.result

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.R
import woowacourse.movie.data.FakeMovieRepository
import woowacourse.movie.data.MovieRepositoryFactory
import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.MovieReservation
import woowacourse.movie.model.board.SeatBoard
import woowacourse.movie.model.board.Seats
import woowacourse.movie.model.date.ScreeningMovie
import woowacourse.movie.repository.MovieRepository
import woowacourse.movie.utils.context
import java.time.LocalDateTime

class ReservationResultActivityTest {
    @Before
    fun setUp() {
        MovieRepositoryFactory.setMovieRepository(repository = FakeMovieRepository())
    }

    @After
    fun tearDown() {
        MovieRepositoryFactory.clear()
    }

    @Test
    @DisplayName("유효한 ID 가 전달되었을 때, 예약 결과가 보여지는지 테스트")
    fun test() {
        launchSuccessScenario()

        onView(ViewMatchers.withId(R.id.cl_reservation_result_error))
            .check(matches(not(isDisplayed())))
        onView(ViewMatchers.withId(R.id.cl_reservation_result_success))
            .check(matches(isDisplayed()))

        MovieRepositoryFactory.clear()
    }

    @Test
    @DisplayName("유효하지 않은 ID 가 전달되었을 때, 에러 화면이 보여지는지 테스트")
    fun test2() {
        ActivityScenario.launch<ReservationResultActivity>(
            ReservationResultActivity.newIntent(
                context,
                ReservationResultActivity.INVALID_RESERVATION_ID,
            ),
        )

        onView(ViewMatchers.withId(R.id.cl_reservation_result_error))
            .check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.cl_reservation_result_success))
            .check(matches(not(isDisplayed())))
    }

    private fun launchSuccessScenario(): ActivityScenario<ReservationResultActivity> {
        MovieRepositoryFactory.setMovieRepository(
            object : MovieRepository {
                override fun screenSeats(
                    screenMovieId: Long,
                    headCount: Int,
                    dateTime: LocalDateTime,
                ): Result<SeatBoard> {
                    throw UnsupportedOperationException()
                }

                override fun screenMovies(): List<ScreeningMovie> {
                    throw UnsupportedOperationException()
                }

                override fun screenMovieById(id: Long): Result<ScreeningMovie> {
                    throw UnsupportedOperationException()
                }

                override fun reserveMovie(
                    id: Long,
                    dateTime: LocalDateTime,
                    count: HeadCount,
                    selectedSeats: Seats,
                ): Result<Long> {
                    TODO("Not yet implemented")
                }

                override fun movieReservationById(id: Long): Result<MovieReservation> {
                    return Result.success(
                        MovieReservation(
                            id = 1,
                            screeningMovie = ScreeningMovie.STUB,
                            screenDateTime = LocalDateTime.now(),
                            headCount = HeadCount(1),
                            seats = Seats(),
                        ),
                    )
                }
            },
        )
        return ActivityScenario.launch<ReservationResultActivity>(
            ReservationResultActivity.newIntent(
                context,
                1L,
            ),
        )
    }
}

package woowacourse.movie.booking

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.movies.MovieReserveService
import woowacourse.movie.domain.movies.MovieToReserve
import woowacourse.movie.ui.booking.BookingContract
import woowacourse.movie.ui.booking.BookingPresenter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingPresenterTest {
    private lateinit var view: BookingContract.View
    private lateinit var presenter: BookingPresenter
    private lateinit var movieReserveService: MovieReserveService
    private lateinit var movieToReserve: MovieToReserve
    private lateinit var selectedDate: LocalDate
    private lateinit var selectedTime: LocalTime

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        movieReserveService = mockk<MovieReserveService>()
        presenter = BookingPresenter(view, 1, movieReserveService)
        selectedDate = LocalDate.of(2025, 1, 1)
        selectedTime = LocalTime.of(1, 1)
        movieToReserve =
            MovieToReserve(
                1,
                LocalDateTime.of(selectedDate, selectedTime),
                1,
            )
        every {
            movieReserveService.createMovieToReserve(
                any(),
                any(),
                any(),
            )
        } returns movieToReserve
    }

    @Test
    fun `increaseHeadCount 호출 시 headCount 2로 증가 후 View에 업데이트 된다`() {
        // given
        every { view.updateHeadCount(any()) } just runs

        // when
        presenter.increaseHeadCount()

        // then
        verify {
            view.updateHeadCount(2)
        }
    }

    @Test
    fun `decreaseHeadCount 호출 시 headCount 감소 후 View에 업데이트 된다`() {
        // given
        every { view.updateHeadCount(any()) } just runs
        presenter.increaseHeadCount()

        // when
        presenter.decreaseHeadCount()

        // then
        verify {
            view.updateHeadCount(1)
        }
    }

    @Test
    fun `onConfirm 호출 시 티켓을 생성하고 좌석 선택 화면으로 이동한다`() {
        // given
        every {
            movieReserveService.createMovieToReserve(
                1,
                LocalDateTime.of(selectedDate, selectedTime),
                1,
            )
        } returns movieToReserve
        presenter.loadAvailableDates()
        presenter.loadAvailableTimes(selectedDate)
        presenter.restoreTime(selectedTime)
        every { view.navigateToSeatsSelection(mockk()) } just runs

        // when
        presenter.onConfirm()

        // then
        verify {
            movieReserveService.createMovieToReserve(
                1,
                LocalDateTime.of(selectedDate, selectedTime),
                1,
            )
            view.navigateToSeatsSelection(movieToReserve)
        }
    }
}

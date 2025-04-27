package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.HeadCount
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.schedule.MovieScheduler
import woowacourse.movie.domain.service.MovieTicketService
import woowacourse.movie.ui.view.booking.BookingContract
import java.time.LocalDate
import java.time.LocalDateTime

class BookingPresenterTest {
    private lateinit var view: BookingContract.View
    private val movieId = 1
    private lateinit var headCount: HeadCount
    private lateinit var movieRepository: MovieRepository
    private lateinit var movieTicketService: MovieTicketService
    private lateinit var presenter: BookingPresenter
    private lateinit var movieScheduler: MovieScheduler
    private lateinit var movie: Movie
    private lateinit var movieTicket: MovieTicket

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        headCount = mockk()
        movieRepository = mockk()
        movieTicketService = mockk()
        movieScheduler = mockk()
        movieTicket = mockk()
        movie =
            mockk<Movie> {
                every { startScreeningDate } returns LocalDate.of(2025, 5, 1)
                every { endScreeningDate } returns LocalDate.of(2025, 5, 20)
                every { title } returns "승부"
                every { runningTime } returns 115
                every { id } returns movieId
            }

        every { movieRepository.getMovieById(movieId) } returns movie
        presenter = BookingPresenter(view, movieId, headCount, movieRepository, movieTicketService)
    }

    @Test
    fun `increaseHeadCount 호출 시 headCount 증가 후 View에 업데이트 된다`() {
        // given
        every { headCount.increase() } just runs
        every { headCount.getCount() } returns 3

        // when
        presenter.increaseHeadCount()

        // then
        verifySequence {
            headCount.increase()
            headCount.getCount()
            view.updateHeadCount(3)
        }
    }

    @Test
    fun `decreaseHeadCount 호출 시 headCount 감소 후 View에 업데이트 된다`() {
        // given
        every { headCount.decrease() } just runs
        every { headCount.getCount() } returns 2

        // when
        presenter.decreaseHeadCount()

        // then
        verifySequence {
            headCount.decrease()
            headCount.getCount()
            view.updateHeadCount(2)
        }
    }

    @Test
    fun `onConfirm 호출 시 티켓을 생성하고 예매 상세 화면으로 이동한다`() {
        // given
        every { movieRepository.getMovieById(movieId) } returns movie
        every {
            movieTicketService.createMovieTicket(
                movieId,
                LocalDateTime.now(),
                2,
            )
        } returns movieTicket
        every { headCount.getCount() } returns 2

        presenter.saveDate(presenter.selectedDate)
        presenter.saveTime(presenter.selectedTime)

        // when
        presenter.onConfirm()

        // then
        verify {
            movieTicketService.createMovieTicket(movieId, LocalDateTime.now(), 2)
            view.navigateToSeatsSelection(movieTicket)
        }
    }
}

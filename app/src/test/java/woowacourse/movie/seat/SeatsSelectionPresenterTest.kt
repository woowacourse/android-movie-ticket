package woowacourse.movie.seat

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.movies.Movie
import woowacourse.movie.domain.movies.MovieRepository
import woowacourse.movie.domain.movies.MovieToReserve
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.SeatPricingPolicy
import woowacourse.movie.domain.seat.SeatService
import woowacourse.movie.domain.seat.SeatState
import woowacourse.movie.domain.ticket.MovieTicket
import woowacourse.movie.domain.ticket.MovieTicketService
import woowacourse.movie.ui.seat.SeatsSelectionContract
import woowacourse.movie.ui.seat.SeatsSelectionPresenter

class SeatsSelectionPresenterTest {
    private lateinit var view: SeatsSelectionContract.View
    private lateinit var presenter: SeatsSelectionPresenter
    private lateinit var movieTicket: MovieTicket
    private lateinit var movieRepository: MovieRepository
    private lateinit var seatPricingPolicy: SeatPricingPolicy
    private lateinit var movie: Movie
    private lateinit var movieToReserve: MovieToReserve
    private lateinit var movieTicketService: MovieTicketService
    private lateinit var seatService: SeatService

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        movieTicket =
            mockk<MovieTicket> {
                every { movieId } returns 1
                every { amount } returns 20000
                every { selectedSeats } returns listOf(Seat(0, 0), Seat(0, 1))
            }
        movieRepository = mockk()

        movie =
            mockk<Movie> {
                every { title } returns "승부"
            }

        every { movieRepository.getMovieById(1) } returns movie

        movieToReserve =
            mockk<MovieToReserve> {
                every { movieId } returns 1
                every { headCount } returns 2
            }
        seatPricingPolicy = mockk()
        every { seatPricingPolicy.calculatePrice() } returns 20000
        movieTicketService = mockk()
        seatService = mockk()
        presenter =
            SeatsSelectionPresenter(
                view,
                movieToReserve,
                movieRepository,
                movieTicketService,
                seatPricingPolicy = seatPricingPolicy,
                seatService = seatService,
            )
    }

    @Test
    fun `onConfirm을 호출하면 예매 상세 화면으로 이동한다`() {
        // given
        every {
            movieTicketService.createMovieTicket(
                any(),
                any(),
                any(),
            )
        } returns movieTicket
        every { view.navigateToBookingSummary(movieTicket) } just runs

        // when
        presenter.onConfirm()

        // then
        verify {
            view.navigateToBookingSummary(movieTicket)
        }
    }

    @Test
    fun `loadMovieTitle를 호출하면 영화 제목이 보인다`() {
        // given
        every { movieRepository.getMovieById(any()) } returns movie
        every { view.showMovieTitle(any()) } just runs

        // when
        presenter.loadMovieTitle()

        // then
        verify {
            movieRepository.getMovieById(any())
            view.showMovieTitle(any())
        }
    }

    @Test
    fun `loadAmount를 호출하면 좌석 가격의 총합이 보인다`() {
        // given

        every { view.showAmount(any()) } just runs

        // when
        presenter.loadAmount()

        // then
        verify {
            view.showAmount(20000)
        }
    }

    @Test
    fun `좌석 클릭 시 SELECTED면 색이 바뀌고 리스트에 추가된다`() {
        // given
        every { seatService.getSeatSate(any(), any()) } returns SeatState.SELECTED
        every { view.changeSeatColor(any(), any(), any()) } just runs
        every { view.showSeatLimitToastMessage() } just runs
        every { seatPricingPolicy.calculatePrice() } returns 10000
        every { movieTicket.amount = any() } just runs
        every { view.showAmount(any()) } just runs

        // when
        presenter.onClickSeat(1, 2)

        // then
        verify {
            view.changeSeatColor(1, 2, true)
            view.showAmount(10000)
        }
    }
}

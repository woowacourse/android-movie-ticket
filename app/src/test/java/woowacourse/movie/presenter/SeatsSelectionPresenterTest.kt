package woowacourse.movie.presenter

import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.domain.policy.PricingPolicy
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.ui.view.seat.SeatState
import woowacourse.movie.ui.view.seat.SeatsSelectionContract

class SeatsSelectionPresenterTest {
    private lateinit var view: SeatsSelectionContract.View
    private lateinit var presenter: SeatsSelectionPresenter
    private lateinit var movieTicket: MovieTicket
    private lateinit var movieRepository: MovieRepository
    private lateinit var pricingPolicy: PricingPolicy
    private lateinit var movie: Movie

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        movieTicket =
            mockk<MovieTicket> {
                every { movieId } returns 1 // getMovieId 호출할 때 1 리턴
                every { amount } returns 20000
                every { selectedSeats } returns mutableListOf("A1", "A2")
            }
        movieRepository = mockk()

        movie =
            mockk<Movie> {
                every { title } returns "승부"
            }

        pricingPolicy = mockk()
        presenter = SeatsSelectionPresenter(view, movieTicket, movieRepository, pricingPolicy)
    }

    @Test
    fun `onConfirm을 호출하면 예매 상세 화면으로 이동한다`() {
        // given
        every { view.navigateToBookingSummary() } just runs

        // when
        presenter.onConfirm()

        // then
        verify {
            view.navigateToBookingSummary()
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
        every { pricingPolicy.calculatePrice() } returns 20000
        every { movieTicket.amount = any() } just runs
        every { view.showAmount(any()) } just runs

        // when
        presenter.loadAmount()

        // then
        verify {
            pricingPolicy.calculatePrice()
            movieTicket.amount = 20000
            view.showAmount(20000)
        }
    }

    @Test
    fun `이미 선택된 좌석을 다시 선택하면 DESELECTED를 반환한다`() {
        // given
        movieTicket.selectedSeats.add("A1")

        // when
        val result = presenter.getSeatResult("A1")

        // then
        result shouldBe SeatState.DESELECTED
    }

    @Test
    fun `좌석이 한계에 도달했으면 LIMIT를 반환한다`() {
        // given
        movieTicket =
            mockk<MovieTicket> {
                every { selectedSeats } returns mutableListOf("A1")
                every { headCount } returns 1
            }
        presenter = SeatsSelectionPresenter(view, movieTicket)

        // when
        val result = presenter.getSeatResult("B1")

        // then
        result shouldBe SeatState.LIMIT
    }

    @Test
    fun `좌석을 선택하면 SELECTED를 반환한다`() {
        // given
        movieTicket =
            mockk<MovieTicket> {
                every { selectedSeats } returns mutableListOf("A3", "A4")
                every { headCount } returns 3
            }
        presenter = SeatsSelectionPresenter(view, movieTicket)

        // when
        val result = presenter.getSeatResult("A1")

        // then
        result shouldBe SeatState.SELECTED
    }
}

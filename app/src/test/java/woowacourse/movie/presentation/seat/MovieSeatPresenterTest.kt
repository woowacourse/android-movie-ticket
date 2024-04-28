package woowacourse.movie.presentation.seat
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.MOVIE
import woowacourse.movie.SEATS
import woowacourse.movie.domain.MovieRepository

@ExtendWith(MockKExtension::class)
class MovieSeatPresenterTest {
    @MockK
    private lateinit var seatContractView: MovieSeatContract.View

    @MockK
    private lateinit var movieRepository: MovieRepository

    private lateinit var seatPresenter: MovieSeatPresenter

    @BeforeEach
    fun setUp() {
        seatPresenter = MovieSeatPresenter(seatContractView, movieRepository)
    }

    @Test
    fun `loadSeats를 통해 데이터를 가져오고, onInitView로 view에 데이터가 전달된다`() {
        every { movieRepository.findMovieById(any()) } returns MOVIE
        every { movieRepository.findSeatsByMovieScreenDateTimeId(any()) } returns SEATS
        every { seatContractView.onInitView(any(), any()) } just runs
        every { seatContractView.onError(any()) } just runs

        seatPresenter.loadSeats(0, 0, 1)

        verify { seatContractView.onInitView(any(), SEATS) }
        verify(exactly = 0) { seatContractView.onError(any()) }
    }

    @Test
    fun `loadSeats를 통해 올바르지 않은 데이터를 전달하면 onError가 호출된다`() {
        every { movieRepository.findMovieById(any()) } returns null
        every { movieRepository.findSeatsByMovieScreenDateTimeId(any()) } returns SEATS
        every { seatContractView.onInitView(any(), any()) } just runs
        every { seatContractView.onError(any()) } just runs

        seatPresenter.loadSeats(0, 0, 1)

        verify(exactly = 0) { seatContractView.onInitView(any(), SEATS) }
        verify(exactly = 1) { seatContractView.onError(any()) }
    }

    @Test
    fun `좌석을 선택하면 해당하는 자리의 가격이 반영된다`() {
        every { movieRepository.findMovieById(any()) } returns MOVIE
        every { movieRepository.findSeatsByMovieScreenDateTimeId(any()) } returns SEATS
        every { seatContractView.onInitView(any(), any()) } just runs
        every { seatContractView.onSeatUpdate(any(), any()) } just Runs
        every { seatContractView.onPriceUpdate(any()) } just Runs
        every { seatContractView.onReservationButtonChanged(any()) } just Runs

        seatPresenter.loadSeats(0, 0, 2)
        seatPresenter.selectSeat(0, SEATS[0], false) // S 티어

        verify { seatContractView.onSeatUpdate(0, isSelected = true) }
        verify { seatContractView.onPriceUpdate(15000) }
        verify { seatContractView.onReservationButtonChanged(any()) }
    }

    @Test
    fun `이미 선택한 좌석을 재선택하면 좌석이 해제되고, 가격이 반영된다`() {
        every { movieRepository.findMovieById(any()) } returns MOVIE
        every { movieRepository.findSeatsByMovieScreenDateTimeId(any()) } returns SEATS
        every { seatContractView.onInitView(any(), any()) } just runs
        every { seatContractView.onSeatUpdate(any(), any()) } just Runs
        every { seatContractView.onPriceUpdate(any()) } just Runs
        every { seatContractView.onReservationButtonChanged(any()) } just Runs

        seatPresenter.loadSeats(0, 0, 1)
        seatPresenter.selectSeat(0, SEATS[0], false) // S 티어
        seatPresenter.selectSeat(0, SEATS[0], true)

        verify { seatContractView.onPriceUpdate(0) }
    }

    @Test
    fun `좌석 선택이 기준치에 닿지 않으면 onReservationButtonChanged로 false가 전달된다`() {
        every { movieRepository.findMovieById(any()) } returns MOVIE
        every { movieRepository.findSeatsByMovieScreenDateTimeId(any()) } returns SEATS
        every { seatContractView.onInitView(any(), any()) } just runs
        every { seatContractView.onSeatUpdate(any(), any()) } just Runs
        every { seatContractView.onPriceUpdate(any()) } just Runs
        every { seatContractView.onReservationButtonChanged(any()) } just Runs

        seatPresenter.loadSeats(0, 0, 2)
        seatPresenter.selectSeat(0, SEATS[0], false) // S 티어

        verify { seatContractView.onSeatUpdate(any(), any()) }
        verify { seatContractView.onPriceUpdate(any()) }
        verify { seatContractView.onReservationButtonChanged(false) }
    }

    @Test
    fun `좌석 선택이 기준치에 닿으면 onReservationButtonChanged로 true가 전달된다`() {
        every { movieRepository.findMovieById(any()) } returns MOVIE
        every { movieRepository.findSeatsByMovieScreenDateTimeId(any()) } returns SEATS
        every { seatContractView.onInitView(any(), any()) } just runs
        every { seatContractView.onSeatUpdate(any(), any()) } just Runs
        every { seatContractView.onPriceUpdate(any()) } just Runs
        every { seatContractView.onReservationButtonChanged(any()) } just Runs

        seatPresenter.loadSeats(0, 0, 1)
        seatPresenter.selectSeat(0, SEATS[0], false) // S 티어

        verify { seatContractView.onSeatUpdate(any(), any()) }
        verify { seatContractView.onPriceUpdate(any()) }
        verify { seatContractView.onReservationButtonChanged(true) }
    }

    @Test
    fun `reservation() 하면 onReservationComplete()가 호출된다`() {
        every { seatContractView.onReservationComplete(any(), any(), any()) } just runs

        seatPresenter.reservation()

        verify { seatContractView.onReservationComplete(any(), any(), any()) }
    }
}

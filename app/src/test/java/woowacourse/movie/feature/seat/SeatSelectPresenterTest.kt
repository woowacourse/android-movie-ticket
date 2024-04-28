package woowacourse.movie.feature.seat

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.SelectedSeats
import woowacourse.movie.feature.setUpForSelectSeat
import woowacourse.movie.model.data.MovieRepository
import woowacourse.movie.model.data.MovieRepositoryImpl
import woowacourse.movie.model.data.TicketRepository
import woowacourse.movie.model.data.TicketRepositoryImpl
import woowacourse.movie.model.seat.Seat
import java.time.LocalDateTime

class SeatSelectPresenterTest {
    private lateinit var view: SeatSelectContract.View
    private val movieRepository: MovieRepository = MovieRepositoryImpl
    private val ticketRepository: TicketRepository = TicketRepositoryImpl
    private lateinit var presenter: SeatSelectPresenter
    private val reservationCount = 2

    @BeforeEach
    fun setUp() {
        view = mockk<SeatSelectContract.View>()
        presenter = SeatSelectPresenter(view, movieRepository, ticketRepository)
    }

    @Test
    fun `영화 데이터를 불러오면 영화 뷰가 초기화된다`() {
        // given
        every { view.initializeMovieView(any()) } just runs

        // when
        presenter.loadMovieData(0L)

        // then
        verify { view.initializeMovieView(any()) }
    }

    @Test
    fun `좌석표를 초기화하면 좌석표 뷰가 초기화된다`() {
        // given
        every { view.initializeSeatTable(any()) } just runs

        // when
        presenter.initializeSeatTable(SelectedSeats(reservationCount), 5, 4)

        // then
        verify { view.initializeSeatTable(any()) }
    }

    @Test
    fun `2행 3열 좌석을 선택하면 10,000원이 화면에 보인다`() {
        // given
        view.setUpForSelectSeat()
        presenter.initializeSeatTable(SelectedSeats(reservationCount), 5, 4)

        // when
        // 2행 3열 좌석은 인덱스 [1][2]에 저장되어 있다
        presenter.selectSeat(1, 2)

        // then
        verify { view.selectSeat(1, 2, false) }
        verify { view.updateReservationAmount(10_000) }
    }

    @Test
    fun `2열 3행, 4열 4행 좌석을 선택하면 25,000원이 화면에 보인다`() {
        // given
        view.setUpForSelectSeat()
        presenter.initializeSeatTable(SelectedSeats(reservationCount), 5, 4)

        // when
        presenter.selectSeat(1, 2)
        presenter.selectSeat(3, 3)

        // then
        verify { view.selectSeat(1, 2, false) }
        verify { view.updateReservationAmount(10_000) }

        verify { view.selectSeat(3, 3, true) }
        verify { view.updateReservationAmount(25_000) }
    }

    @Test
    fun `2행 3열, 4행 4열을 선택한 상태에서 2행 3열을 다시 선택하면, 금액이 줄어들고 선택이 해제된다`() {
        // given
        view.setUpForSelectSeat()
        presenter.initializeSeatTable(SelectedSeats(reservationCount), 5, 4)
        presenter.selectSeat(1, 2)
        presenter.selectSeat(3, 3)

        // when
        presenter.selectSeat(1, 2)

        // then
        verify { view.updateReservationAmount(15_000) }
        verify { view.unselectSeat(1, 2) }
    }

    @Test
    fun `예매 인원이 2명이고 3개의 좌석을 선택하면, 더이상 선택할 수 없다는 메시지를 보여준다`() {
        // given
        view.setUpForSelectSeat()
        presenter.initializeSeatTable(SelectedSeats(reservationCount), 5, 4)
        presenter.selectSeat(1, 2)
        presenter.selectSeat(3, 3)

        // when
        presenter.selectSeat(2, 0)

        // then
        verify { view.showCannotSelectSeat() }
    }

    @Test
    fun `확인 버튼을 클릭하면 예매 완료 페이지로 이동한다`() {
        // given
        every { view.moveReservationCompleteView(any()) } just runs
        every { view.initializeSeatTable(any()) } just runs
        presenter.initializeSeatTable(SelectedSeats(reservationCount), 5, 4)

        // when
        presenter.confirmSeatSelection(0L, LocalDateTime.of(2024, 4, 1, 9, 0))

        // then
        verify { view.moveReservationCompleteView(any()) }
    }

    @Test
    fun `선택한 좌석을 2행 3열과 4행 1열으로 변경한다`() {
        // given
        view.setUpForSelectSeat()
        presenter.initializeSeatTable(SelectedSeats(reservationCount), 5, 4)

        // when
        presenter.updateSelectedSeats(
            SelectedSeats(reservationCount, Seat(2, 3), Seat(4, 1)),
        )

        // then
        verify { view.selectSeat(1, 2, true) }
        verify { view.selectSeat(3, 0, true) }
        verify { view.updateReservationAmount(10_000) }
        verify { view.updateReservationAmount(25_000) }
    }
}

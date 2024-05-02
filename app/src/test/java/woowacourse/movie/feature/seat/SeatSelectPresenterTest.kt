package woowacourse.movie.feature.seat

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.SelectedSeats
import woowacourse.movie.feature.seat.ui.SeatSelectMovieUiModel
import woowacourse.movie.feature.seat.ui.SeatSelectTableUiModel
import woowacourse.movie.feature.setUpForSelectSeat
import woowacourse.movie.model.data.MovieRepository
import woowacourse.movie.model.data.MovieRepositoryImpl
import woowacourse.movie.model.data.TicketRepository
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.seat.SeatTable
import java.time.LocalDateTime

class SeatSelectPresenterTest {
    private lateinit var view: SeatSelectContract.View
    private val movieRepository: MovieRepository = MovieRepositoryImpl
    private lateinit var ticketRepository: TicketRepository
    private lateinit var presenter: SeatSelectPresenter
    private val reservationCount = 2

    @BeforeEach
    fun setUp() {
        view = mockk<SeatSelectContract.View>()
        ticketRepository = mockk<TicketRepository>()
        presenter = SeatSelectPresenter(view, movieRepository, ticketRepository)
    }

    @Test
    fun `영화 데이터를 불러온다`() {
        // given
        val movieUiModelSlot = slot<SeatSelectMovieUiModel>()
        every { view.initializeMovieView(capture(movieUiModelSlot)) } just runs

        // when
        presenter.loadMovieData(0L)

        // then
        val actual = movieUiModelSlot.captured
        val expected = SeatSelectMovieUiModel.from(movieRepository.find(0L))
        assertThat(actual).isEqualTo(expected)
        verify { view.initializeMovieView(actual) }
    }

    @Test
    fun `좌석표를 초기화한다`() {
        // given
        val seatsUiModelSlot = slot<List<List<SeatSelectTableUiModel>>>()
        every { view.initializeSeatViews(capture(seatsUiModelSlot)) } just runs

        // when
        presenter.loadSeatTable(SelectedSeats(reservationCount), 5, 4)

        // then
        val actual = seatsUiModelSlot.captured
        val expected = SeatSelectTableUiModel.from(SeatTable(5, 4))
        assertThat(actual).isEqualTo(expected)
        verify { view.initializeSeatViews(actual) }
    }

    @Test
    fun `2행 3열 좌석을 선택하면 금액이 10,000원이다`() {
        // given
        val reservationAmountSlot = slot<Int>()
        view.setUpForSelectSeat()
        every { view.updateReservationAmount(capture(reservationAmountSlot)) } just runs
        presenter.loadSeatTable(SelectedSeats(reservationCount), 5, 4)

        // when
        // 2행 3열 좌석은 인덱스 [1][2]에 저장되어 있다
        presenter.selectSeat(1, 2)

        // then
        val actual = reservationAmountSlot.captured
        assertThat(actual).isEqualTo(10_000)
        verify { view.selectSeat(1, 2, false) }
        verify { view.updateReservationAmount(actual) }
    }

    @Test
    fun `2열 3행, 4열 4행 좌석을 선택하면 금액이 25,000원이다`() {
        // given
        val reservationAmountSlot = slot<Int>()
        view.setUpForSelectSeat()
        every { view.updateReservationAmount(capture(reservationAmountSlot)) } just runs
        presenter.loadSeatTable(SelectedSeats(reservationCount), 5, 4)

        // when
        presenter.selectSeat(1, 2)
        presenter.selectSeat(3, 3)

        // then
        val actual = reservationAmountSlot.captured
        assertThat(actual).isEqualTo(25_000)
        verify { view.selectSeat(1, 2, false) }
        verify { view.selectSeat(3, 3, true) }
        verify { view.updateReservationAmount(actual) }
    }

    @Test
    fun `2행 3열, 4행 4열을 선택한 상태에서 2행 3열을 다시 선택하면, 금액이 줄어들고 선택이 해제된다`() {
        // given
        val reservationAmountSlot = slot<Int>()
        view.setUpForSelectSeat()
        every { view.updateReservationAmount(capture(reservationAmountSlot)) } just runs
        presenter.loadSeatTable(SelectedSeats(reservationCount), 5, 4)
        presenter.selectSeat(1, 2)
        presenter.selectSeat(3, 3)

        // when
        presenter.selectSeat(1, 2)

        // then
        val actual = reservationAmountSlot.captured
        assertThat(actual).isEqualTo(15_000)
        verify { view.updateReservationAmount(actual) }
        verify { view.unselectSeat(1, 2) }
    }

    @Test
    fun `예매 인원이 2명이고 3개의 좌석을 선택하면, 더이상 선택할 수 없다`() {
        // given
        view.setUpForSelectSeat()
        presenter.loadSeatTable(SelectedSeats(reservationCount), 5, 4)
        presenter.selectSeat(1, 2)
        presenter.selectSeat(3, 3)

        // when
        presenter.selectSeat(2, 0)

        // then
        verify { view.showCannotSelectSeat() }
    }

    @Test
    fun `좌석 선택을 끝내면 예매 완료 페이지로 이동한다`() {
        // given
        val ticketIdSlot = slot<Long>()
        every { ticketRepository.save(any(), any(), any()) } returns 0L
        every { view.moveReservationCompleteView(capture(ticketIdSlot)) } just runs
        every { view.initializeSeatViews(any()) } just runs
        presenter.loadSeatTable(SelectedSeats(reservationCount), 5, 4)

        // when
        presenter.finishSeatSelection(0L, LocalDateTime.of(2024, 4, 1, 9, 0))

        // then
        val actual = ticketIdSlot.captured
        assertThat(actual).isEqualTo(0L)
        verify { view.moveReservationCompleteView(actual) }
    }

    @Test
    fun `선택한 좌석을 2행 3열과 4행 1열으로 변경한다`() {
        // given
        val reservationAmountSlot = slot<Int>()
        view.setUpForSelectSeat()
        every { view.updateReservationAmount(capture(reservationAmountSlot)) } just runs
        presenter.loadSeatTable(SelectedSeats(reservationCount), 5, 4)

        // when
        presenter.updateSelectedSeats(
            SelectedSeats(reservationCount, Seat(2, 3), Seat(4, 1)),
        )

        // then
        val actual = reservationAmountSlot.captured
        assertThat(actual).isEqualTo(25_000)
        verify { view.selectSeat(1, 2, true) }
        verify { view.selectSeat(3, 0, true) }
        verify { view.updateReservationAmount(actual) }
    }
}

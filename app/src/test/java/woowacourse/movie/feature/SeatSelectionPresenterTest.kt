package woowacourse.movie.feature

import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.mockk.verifyAll
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.seat.Seats
import woowacourse.movie.feature.model.seat.SeatsUiModel
import woowacourse.movie.feature.seatSelection.SeatSelectionContract
import woowacourse.movie.feature.seatSelection.SeatSelectionPresenter
import woowacourse.movie.fixtures.SEATS_FULL
import woowacourse.movie.fixtures.TICKET

class SeatSelectionPresenterTest {
    private lateinit var presenter: SeatSelectionPresenter
    private lateinit var view: SeatSelectionContract.View
    private lateinit var seats: Seats

    @BeforeEach
    fun setup() {
        view = mockk(relaxed = true)
        seats = mockk()
        presenter = SeatSelectionPresenter(view)
    }

    @Test
    fun `loadReservationInfo 호출 시 예약 정보를 표시하고 가격 정보를 갱신한다`() {
        // when
        presenter.loadReservationInfo(TICKET)

        // then
        verifyAll {
            view.showReservationInfo(TICKET)
            view.updateTotalPrice(0)
        }
    }

    @Test
    fun `restoreReservationInfo 호출 시 선택된 좌석을 표시하고 가격 정보를 갱신한다`() {
        // when
        presenter.restoreReservationInfo(SEATS_FULL)

        // then
        verify {
            view.toggleSeat(0, false)
            view.toggleSeat(1, false)
            view.toggleSeat(2, false)
            view.updateTotalPrice(30000)
        }
    }

    @Test
    fun `finishSelection 호출 시 좌석 선택이 끝났으면 다이얼로그를 표시한다`() {
        // when
        presenter.loadReservationInfo(TICKET)
        presenter.selectSeat(0)
        presenter.selectSeat(1)
        presenter.selectSeat(2)
        presenter.finishSelection()

        // then
        verify { view.showAlertDialog() }
    }

    @Test
    fun `finishSelection 호출 시 좌석 선택이 끝나지 않았으면 토스트를 표시한다`() {
        // when
        presenter.loadReservationInfo(TICKET)
        presenter.finishSelection()

        // then
        verify { view.showSelectionNotFinishedToast(3) }
    }

    @Test
    fun `confirmSelection 호출 시 예매 완료 화면으로 이동한다`() {
        val seats = slot<SeatsUiModel>()

        // when
        presenter.loadReservationInfo(TICKET)
        presenter.confirmSelection()

        // then
        verify { view.goToReservationResult(TICKET, capture(seats)) }
        assertThat(seats.captured.capacity).isEqualTo(presenter.seats.capacity)
        assertThat(seats.captured.seats).isEqualTo(presenter.seats.seats)
    }
}

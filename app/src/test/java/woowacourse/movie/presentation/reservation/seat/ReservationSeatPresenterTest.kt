package woowacourse.movie.presentation.reservation.seat

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.cinema.screen.Screen
import woowacourse.movie.presentation.model.ReservationInfoUiModel
import woowacourse.movie.presentation.model.SeatTypeUiModel
import woowacourse.movie.presentation.model.SeatUiModel
import woowacourse.movie.presentation.model.toUiModel
import woowacourse.movie.presentation.view.reservation.seat.ReservationSeatContract
import woowacourse.movie.presentation.view.reservation.seat.ReservationSeatPresenter
import java.time.LocalDateTime

class ReservationSeatPresenterTest {
    private lateinit var view: ReservationSeatContract.View
    private lateinit var presenter: ReservationSeatContract.Presenter
    private val fakeReservationInfo =
        ReservationInfoUiModel(
            "해리포터",
            LocalDateTime.of(2025, 4, 1, 11, 0),
            2,
            listOf(),
        )

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = ReservationSeatPresenter(view)
    }

    @Test
    fun `예매 정보를 불러온다`() {
        // Given: view의 setScreen 동작을 설정한다
        every { view.setScreen(any(), any(), any(), any(), any()) } just Runs

        // When: presenter가 데이터를 불러온다
        presenter.fetchData(fakeReservationInfo, Screen.DEFAULT_SCREEN.toUiModel())

        // Then: view에 setScreen이 호출되어야 한다
        verify { view.setScreen(fakeReservationInfo, any(), any(), any(), any()) }
    }

    @Test
    fun `예매 정보를 불러오지 못하면 메시지를 노출한다`() {
        // Given: view의 notifyInvalidReservationInfo 동작을 설정한다
        every { view.notifyInvalidReservationInfo() } just Runs

        // When: presenter가 데이터를 불러온다
        presenter.fetchData(null, Screen.DEFAULT_SCREEN.toUiModel())

        // Then: view에 notifyInvalidReservationInfo 호출되어야 한다
        verify { view.notifyInvalidReservationInfo() }
    }

    @Test
    fun `선택한 좌석 정보를 갱신한다`() {
        val seat = SeatUiModel(0, 1, SeatTypeUiModel.B_CLASS)

        // Given: 초기 데이터 로딩과 updateSeatStatus 동작을 설정한다
        every { view.setScreen(any(), any(), any(), any(), any()) } just Runs
        every { view.updateSeatState(any(), any(), any()) } just Runs
        presenter.fetchData(fakeReservationInfo, Screen.DEFAULT_SCREEN.toUiModel())

        // When: 좌석을 선택하여 업데이트한다
        presenter.updateSeat(seat)

        // Then: 선택한 좌석 정보로 view의 updateSeatStatus가 호출되어야 한다
        verify { view.updateSeatState(seat, any(), any()) }
    }

    @Test
    fun `좌석 상태를 갱신하지 못한 경우 메시지를 노출한다`() {
        val seat = SeatUiModel(0, 1, SeatTypeUiModel.B_CLASS)

        // Given: view의 동작을 설정한다
        every { view.setScreen(any(), any(), any(), any(), any()) } just Runs
        every { view.updateSeatState(any(), any(), any()) } just Runs
        every { view.notifySeatUpdateFailed(any()) } just Runs
        presenter.fetchData(fakeReservationInfo, Screen.DEFAULT_SCREEN.toUiModel())
        presenter.updateSeat(seat)
        presenter.updateSeat(seat.copy(col = 2))

        // When: 좌석을 선택하여 업데이트한다
        presenter.updateSeat(seat.copy(col = 3))

        // Then: view에 notifySeatUpdateFailed 호출되어야 한다
        verify { view.notifySeatUpdateFailed(any()) }
    }

    @Test
    fun `티켓을 발행한다`() {
        val seat = SeatUiModel(0, 1, SeatTypeUiModel.B_CLASS)

        // Given: 초기 데이터 로딩, 좌석 업데이트, 티켓 발행 알림 동작을 설정한다
        every { view.setScreen(any(), any(), any(), any(), any()) } just Runs
        every { view.updateSeatState(any(), any(), any()) } just Runs
        every { view.notifyPublishedTickets(any()) } just Runs
        presenter.fetchData(fakeReservationInfo, Screen.DEFAULT_SCREEN.toUiModel())
        presenter.updateSeat(seat)

        // When: 티켓을 발행한다
        presenter.publishTickets()

        // Then: view에 notifyPublishedTickets가 호출되어야 한다
        verify { view.notifyPublishedTickets(any()) }
    }
}

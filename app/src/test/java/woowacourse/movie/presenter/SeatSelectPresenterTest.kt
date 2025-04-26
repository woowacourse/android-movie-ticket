package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Fixture
import woowacourse.movie.view.reservation.seat.SeatSelectContract
import woowacourse.movie.view.reservation.seat.SeatSelectPresenter

class SeatSelectPresenterTest {
    private lateinit var presenter: SeatSelectContract.Presenter
    private lateinit var view: SeatSelectContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = SeatSelectPresenter(view)
    }

    @Test
    fun `데이터를 가져오면 영화 제목과 기본 가격을 설정한다`() {
        // given
        every { view.initReservationInfo(any(), any()) } just Runs

        // when: 데이터를 가져오면
        presenter.fetchData { Fixture.dummyTicket }

        // then: 영화 제목과 기본 가격을 설정한다
        verify { view.initReservationInfo(any(), any()) }
    }

    @Test
    fun `티켓 정보를 불러오지 못하면 에러 다이얼로그를 호출한다`() {
        every { view.showErrorDialog() } just Runs

        presenter.fetchData { null }

        verify { view.showErrorDialog() }
    }

    @Test
    fun `선택되지 않은 좌석을 클릭하면 좌석 상태를 활성화한다`() {
        every { view.initReservationInfo(any(), any()) } just Runs
        every { view.showSeatCountError(any()) } just Runs
        every { view.updateSeatSelected(any()) } just Runs
        every { view.updateTotalPrice(any()) } just Runs
        every { view.updateConfirmButtonState(any()) } just Runs

        presenter.fetchData { Fixture.dummyTicket }
        presenter.onSeatClicked("A1")

        verify { view.updateSeatSelected("A1") }
        verify { view.updateConfirmButtonState(false) }
    }

    @Test
    fun `이미 선택된 좌석 다시 클릭하면 좌석 상태를 비활성화한다`() {
        every { view.initReservationInfo(any(), any()) } just Runs
        every { view.updateSeatSelected(any()) } just Runs
        every { view.updateSeatDeselected(any()) } just Runs
        every { view.updateTotalPrice(any()) } just Runs
        every { view.updateConfirmButtonState(any()) } just Runs

        presenter.fetchData { Fixture.dummyTicket }
        presenter.onSeatClicked("A1")
        presenter.onSeatClicked("A1")

        verify { view.updateSeatDeselected("A1") }
    }

    @Test
    fun `티켓 개수만큼 좌석을 선택하면 확인 버튼을 활성화한다`() {
        every { view.initReservationInfo(any(), any()) } just Runs
        every { view.updateSeatSelected(any()) } just Runs
        every { view.updateSeatDeselected(any()) } just Runs
        every { view.updateTotalPrice(any()) } just Runs
        every { view.updateConfirmButtonState(any()) } just Runs

        val ticket = Fixture.dummyTicket.copy(count = 2)
        presenter.fetchData { ticket }
        presenter.onSeatClicked("A1")
        presenter.onSeatClicked("A2")

        verify { view.updateConfirmButtonState(true) }
    }

    @Test
    fun `티켓 개수를 초과하면 다이얼로그를 보여준다`() {
        every { view.initReservationInfo(any(), any()) } just Runs
        every { view.showSeatCountError(any()) } just Runs
        every { view.updateSeatSelected(any()) } just Runs
        every { view.updateTotalPrice(any()) } just Runs
        every { view.updateConfirmButtonState(any()) } just Runs
        val ticket = Fixture.dummyTicket.copy(count = 1)

        presenter.fetchData { ticket }
        presenter.onSeatClicked("A1")
        presenter.onSeatClicked("A2")

        verify { view.showSeatCountError(1) }
    }
}

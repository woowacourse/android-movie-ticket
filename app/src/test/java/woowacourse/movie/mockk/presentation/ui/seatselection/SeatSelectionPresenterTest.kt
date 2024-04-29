package woowacourse.movie.mockk.presentation.ui.seatselection

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.basic.utils.getDummyReservationInfo
import woowacourse.movie.basic.utils.getDummyScreen
import woowacourse.movie.basic.utils.getDummySeat
import woowacourse.movie.basic.utils.getDummySeatBoard
import woowacourse.movie.basic.utils.toColumnIndex
import woowacourse.movie.domain.repository.ReservationRepository
import woowacourse.movie.domain.repository.ScreenRepository
import woowacourse.movie.presentation.model.MessageType
import woowacourse.movie.presentation.model.MessageType.ReservationSuccessMessage
import woowacourse.movie.presentation.ui.seatselection.SeatSelectionContract
import woowacourse.movie.presentation.ui.seatselection.SeatSelectionPresenter

@ExtendWith(MockKExtension::class)
class SeatSelectionPresenterTest {
    @MockK
    private lateinit var view: SeatSelectionContract.View

    private lateinit var presenter: SeatSelectionContract.Presenter

    @MockK
    private lateinit var screenRepository: ScreenRepository

    @MockK
    private lateinit var reservationRepository: ReservationRepository

    @BeforeEach
    fun setUp() {
        presenter = SeatSelectionPresenter(view, screenRepository, reservationRepository)
    }

    @Test
    fun `SeatSelectionPresenter가 유효한 상영 id값으로 loadScreen()을 했을 때, view에게 screen 정보를 전달한다`() {
        // given
        every { screenRepository.findByScreenId(any()) } returns Result.success(getDummyScreen())
        every { view.showScreen(any()) } just runs

        // when
        presenter.loadScreen(1)

        // then
        verify { view.showScreen(getDummyScreen()) }
    }

    @Test
    fun `SeatSelectionPresenter가 유효하지 않은 상영 id값으로 loadScreen()을 했을 때, view에게 back과 throwable를 전달한다`() {
        // given
        every { screenRepository.findByScreenId(any()) } returns
            Result.failure(
                NoSuchElementException(),
            )
        every { view.showToastMessage(e = any()) } just runs
        every { view.back() } just runs

        // when
        presenter.loadScreen(1)

        // then
        verify { view.showToastMessage(e = any()) }
        verify { view.back() }
    }

    @Test
    fun `SeatSelectionPresenter가 유효한 상영 id값으로 loadSeatBoard()를 했을 때, view에게 좌석 정보와 클릭 설정에 대한 좌석 정보를 전달한다`() {
        // given
        every { screenRepository.loadSeatBoard(any()) } returns Result.success(getDummySeatBoard())
        every { view.showSeatBoard(any()) } just runs
        every { view.initClickListener(any()) } just runs

        // when
        presenter.loadSeatBoard(1)

        // then
        verify { view.showSeatBoard(getDummySeatBoard().seats) }
        verify { view.initClickListener(getDummySeatBoard().seats) }
    }

    @Test
    fun `SeatSelectionPresenter가 유효하지 않은 상영 id값으로 loadSeatBoard()를 했을 때, view에게 back과 throwable를 전달한다`() {
        // given
        every { screenRepository.loadSeatBoard(any()) } returns
            Result.failure(
                NoSuchElementException(),
            )
        every { view.showToastMessage(e = any()) } just runs
        every { view.back() } just runs

        // when
        presenter.loadSeatBoard(1)

        // then
        verify { view.showToastMessage(e = any()) }
        verify { view.back() }
    }

    @Test
    fun `SeatSelectionPresenter가 loadSeatBoard()를 했을 때, 예기치 않은 오류가 발생하면 view에게 back과 throwable를 전달한다`() {
        // given
        every { screenRepository.loadSeatBoard(any()) } returns Result.failure(Exception())
        every { view.showToastMessage(e = any()) } just runs
        every { view.back() } just runs

        // when
        presenter.loadSeatBoard(1)

        // then
        verify { view.showToastMessage(e = any()) }
        verify { view.back() }
    }

    @Test
    fun `SeatSelectionPresenter가 남은 좌석을 선택(clickSeat()) 했을 때, 선택할 티켓이 있다면 view에게 좌석을 선택하라는 정보(selectSeat)를 전달한다`() {
        // given
        val reservationInfo = getDummyReservationInfo()
        val seat = getDummySeat()

        every { screenRepository.loadSeatBoard(any()) } returns Result.success(getDummySeatBoard())
        every { view.selectSeat(any(), any()) } just runs

        // when
        presenter.updateUiModel(reservationInfo)
        presenter.clickSeat(seat)

        // then
        verify { view.selectSeat(seat.column.toColumnIndex(), seat.row) }
    }

    @Test
    fun `SeatSelectionPresenter가 남은 좌석을 선택(clickSeat()) 했을 때, 선택할 티켓이 없다면 view에게 snackBar message(AllSeatsSelectedMessage)를 전달한다`() {
        // given
        val reservationInfo = getDummyReservationInfo().copy(ticketCount = 0)
        val seat = getDummySeat()

        every { screenRepository.loadSeatBoard(any()) } returns Result.success(getDummySeatBoard())
        every { view.showSnackBar(messageType = any()) } just runs

        // when
        presenter.updateUiModel(reservationInfo)
        presenter.clickSeat(seat)

        // then
        verify { view.showSnackBar(MessageType.AllSeatsSelectedMessage(reservationInfo.ticketCount)) }
    }

    @Test
    fun `SeatSelectionPresenter가 이미 선택된 좌석을 선택(clickSeat()) 했을 때, view에게 좌석 선택 해제라는 정보(unselectSeat)를 전달한다`() {
        // given
        val reservationInfo = getDummyReservationInfo()
        val seat = getDummySeat()

        every { screenRepository.loadSeatBoard(any()) } returns Result.success(getDummySeatBoard())
        every { view.selectSeat(any(), any()) } just runs
        every { view.unselectSeat(any(), any()) } just runs

        // when
        presenter.updateUiModel(reservationInfo)
        presenter.clickSeat(seat)
        presenter.clickSeat(seat)

        // then
        verify { view.unselectSeat(seat.column.toColumnIndex(), seat.row) }
    }

    @Test
    fun `SeatSelectionPresenter가 선택된 좌석들의 가격을 계산(calculateSeat) 했을 때, view에게 총 결제 금액에 대한 정보(showTotalPrice)를 전달한다`() {
        // given
        val reservationInfo = getDummyReservationInfo()
        val seat = getDummySeat()

        every { screenRepository.loadSeatBoard(any()) } returns Result.success(getDummySeatBoard())
        every { view.selectSeat(any(), any()) } just runs
        every { view.showTotalPrice(any()) } just runs

        // when
        presenter.updateUiModel(reservationInfo)
        presenter.clickSeat(seat)
        presenter.calculateSeat()

        // then
        verify { view.showTotalPrice(seat.seatRank.price) }
    }

    @Test
    fun `SeatSelectionPresenter가 좌석의 개수와 티켓의 개수가 같을 때(calculateSeat), view에게 버튼을 활성화 하라는 정보(buttonEnabled)를 전달한다()`() {
        // given
        val reservationInfo = getDummyReservationInfo().copy(ticketCount = 1)
        val seat = getDummySeat()

        every { screenRepository.loadSeatBoard(any()) } returns Result.success(getDummySeatBoard())
        every { view.selectSeat(any(), any()) } just runs
        every { view.buttonEnabled(any()) } just runs

        // when
        presenter.updateUiModel(reservationInfo)
        presenter.clickSeat(seat)
        presenter.checkAllSeatsSelected()

        // then
        verify { view.buttonEnabled(true) }
    }

    @Test
    fun `SeatSelectionPresenter가 좌석의 개수와 티켓의 개수가 다를 때(calculateSeat), view에게 버튼을 활성화 하지말라는 정보(buttonEnabled)를 전달한다()`() {
        // given
        val reservationInfo = getDummyReservationInfo().copy(ticketCount = 2)
        val seat = getDummySeat()

        every { screenRepository.loadSeatBoard(any()) } returns Result.success(getDummySeatBoard())
        every { view.selectSeat(any(), any()) } just runs
        every { view.buttonEnabled(any()) } just runs

        // when
        presenter.updateUiModel(reservationInfo)
        presenter.clickSeat(seat)
        presenter.checkAllSeatsSelected()

        // then
        verify { view.buttonEnabled(false) }
    }

    @Test
    fun `SeatSelectionPresenter가 예매 완료 버튼을 누르면(reverse), view에게 메시지(ReservationSuccessMessage)와 결과 화면으로 이동하라는 정보를 전달한다()`() {
        // given
        every { screenRepository.findByScreenId(any()) } returns Result.success(getDummyScreen())
        every {
            reservationRepository.saveReservation(
                any(),
                any(),
                any(),
                any(),
            )
        } returns Result.success(1)
        every { view.showScreen(any()) } just runs
        every { view.showToastMessage(ReservationSuccessMessage) } just runs
        every { view.navigateToReservation(any()) } just runs

        // when
        presenter.loadScreen(1)
        presenter.updateUiModel(getDummyReservationInfo())
        presenter.reserve()

        // then
        verify { view.showToastMessage(ReservationSuccessMessage) }
        verify { view.navigateToReservation(1) }
    }

    @Test
    fun `SeatSelectionPresenter가 예매 정보가 잘못되었을 때 예매 완료 버튼을 누르면(reverse), view에게 메시지(Exception)와 뒤로 돌아가라고() 전달한다(back)`() {
        // given
        val exception = NoSuchElementException()
        every { screenRepository.findByScreenId(any()) } returns Result.success(getDummyScreen())
        every {
            reservationRepository.saveReservation(
                any(),
                any(),
                any(),
                any(),
            )
        } returns Result.failure(exception)
        every { view.showScreen(any()) } just runs
        every { view.showSnackBar(e = any()) } just runs
        every { view.back() } just runs

        // when
        presenter.loadScreen(1)
        presenter.updateUiModel(getDummyReservationInfo())
        presenter.reserve()

        // then
        verify { view.showSnackBar(e = exception) }
        verify { view.back() }
    }
}

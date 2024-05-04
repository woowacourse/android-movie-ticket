package woowacourse.movie.presentation.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.repository.ScreeningMovieInfoRepositoryImpl
import woowacourse.movie.domain.model.reservation.ScreeningMovieInfo
import woowacourse.movie.domain.repository.SeatRepository
import woowacourse.movie.presentation.contract.SeatSelectionContract

class SeatSelectionPresenterTest {
    private lateinit var view: SeatSelectionContract.View
    private lateinit var presenter: SeatSelectionPresenterImpl
    private lateinit var seatRepository: SeatRepository
    private lateinit var screeningMovieInfoRepository: ScreeningMovieInfoRepositoryImpl
    private lateinit var movieInfo: ScreeningMovieInfo

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        seatRepository = mockk(relaxed = true)
        screeningMovieInfoRepository = mockk(relaxed = true)
        movieInfo = mockk(relaxed = true)
        screeningMovieInfoRepository.saveMovieInfo(movieInfo)

        presenter = SeatSelectionPresenterImpl(2, seatRepository, screeningMovieInfoRepository)
        presenter.attachView(view)
    }

    @Test
    fun `좌석 배치도 정보를 불러와 view에게 좌석 배치도를 보여줄 것을 요청한다`() {
        // when
        presenter.loadSeatingChart()

        // then
        verify { view.showSeatingChart(any(), any(), any()) }
    }

    @Test
    fun `좌석 선택 시 view에게 총 금액과 확인 버튼의 활성 상태를 변경할 것을 요청한다`() {
        // when
        presenter.selectSeat(0, 0)

        // then
        verify { view.updateTotalPrice(any()) }
        verify { view.changeConfirmClickable(any()) }
    }

    @Test
    fun `좌석 선택 시 view에게 선택한 좌석의 색상을 변경할 것을 요청한다`() {
        // when
        presenter.selectSeat(0, 0)

        // then
        verify { view.changeSeatColor(0, 0) }
    }

    @Test
    fun `예매 수 만큼 선택되었을 때 좌석 선택 시, 좌석을 모두 선택했음을 알리는 메시지를 출력하도록 요청한다`() {
        // given
        presenter.selectSeat(0, 0)
        presenter.selectSeat(0, 1)

        // when
        presenter.selectSeat(1, 0)

        // then
        verify { view.showAlreadyFilledSeatsSelectionMessage() }
    }

    @Test
    fun `예매 완료 버튼을 눌렀을 때 예매 결과 화면으로 이동할 것을 요청한다`() {
        // given
        presenter.selectSeat(0, 0)
        presenter.selectSeat(0, 1)

        // when
        presenter.onAcceptButtonClicked()

        // then
        verify { view.moveToReservationResult(any()) }
    }
}

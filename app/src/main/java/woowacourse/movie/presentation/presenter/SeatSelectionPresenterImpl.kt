package woowacourse.movie.presentation.presenter

import woowacourse.movie.data.repository.ScreeningMovieInfoRepositoryImpl
import woowacourse.movie.data.repository.SeatRepositoryImpl
import woowacourse.movie.domain.model.reservation.MovieTicket
import woowacourse.movie.domain.model.reservation.ReservationInfo
import woowacourse.movie.domain.repository.SeatRepository
import woowacourse.movie.presentation.contract.SeatSelectionContract
import woowacourse.movie.presentation.uimodel.MovieTicketUiModel

class SeatSelectionPresenterImpl(
    reservationCount: Int,
    seatRepository: SeatRepository = SeatRepositoryImpl,
) : SeatSelectionContract.Presenter {
    private var view: SeatSelectionContract.View? = null
    private val reservationInfo =
        ReservationInfo(reservationCount, seatRepository.getSeatingChart())
    private val screeningMovieInfoRepository = ScreeningMovieInfoRepositoryImpl

    override fun attachView(view: SeatSelectionContract.View) {
        this.view = view
        onViewSetUp()
    }

    override fun detachView() {
        this.view = null
    }

    override fun onViewSetUp() {
        loadSeatingChart()
    }

    // TODO: 좌석 배치도 정보를 repository에서 불러와서, 좌석 예약 정보 설정하기
    override fun loadSeatingChart() {
        view?.showSeatingChart(
            reservationInfo.seatingChart.rowCount,
            reservationInfo.seatingChart.colCount,
            reservationInfo.seatingChart.getSeatRankInfo(),
        )
    }

    // TODO: 뷰에서 선택된 좌석을 ReservationInfo에 업데이트 - 선택 or 선택 해제
    override fun selectSeat(
        row: Int,
        col: Int,
    ) {
        when (reservationInfo.selectedSeats.tryAddOrDeleteSeat(row, col)) {
            true -> view?.changeSeatColor(row, col)
            false -> view?.showAlreadyFilledSeatsSelectionMessage()
        }
        view?.updateTotalPrice(reservationInfo.selectedSeats.totalPrice())
        view?.changeConfirmClickable(reservationInfo.selectedSeats.isMatchedTheCount())
    }

    override fun onAcceptButtonClicked() {
        val movieTicketUiModel =
            MovieTicketUiModel(
                MovieTicket(
                    0,
                    screeningMovieInfoRepository.getScreeningMovieInfo()!!,
                    reservationInfo,
                ),
            )
        view?.moveToReservationResult(movieTicketUiModel)
    }
}

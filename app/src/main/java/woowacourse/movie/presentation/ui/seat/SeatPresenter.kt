package woowacourse.movie.presentation.ui.seat

import woowacourse.movie.domain.repository.MovieTicketRepository
import woowacourse.movie.domain.repository.SeatRepository
import woowacourse.movie.presentation.uimodel.SeatSelectionResult
import woowacourse.movie.presentation.uimodel.SeatUIModel
import woowacourse.movie.presentation.uimodel.SeatsUIModel

class SeatPresenter(
    private val view: SeatContract.View,
    private val movieTicketRepository: MovieTicketRepository,
    private val seatRepository: SeatRepository,
    private val movieTicketId: Int,
) : SeatContract.Presenter {
    private lateinit var seatsUIModel: SeatsUIModel
    private var reservationCount: Int = 0

    init {
        if (movieTicketId <= INVALID_MOVIE_TICKET_ID) {
            view.showMessage(ERROR_MESSAGE.format(INVALID_MOVIE_TICKET_ID_MESSAGE))
        } else {
            loadScreeningInformation()
        }
    }

    override fun loadScreeningInformation() {
        runCatching {
            movieTicketRepository.getMovieTicket(movieTicketId)
        }.onSuccess { movieTicket ->
            view.initClickListener()
            view.showMovieTitle(movieTicket.movieTitle)
            reservationCount = movieTicket.reservationCount
            setUpSeats(reservationCount)
        }.onFailure {
            view.showMessage(ERROR_MESSAGE.format(it.message ?: ""))
        }
    }

    private fun setUpSeats(reservationCount: Int) {
        val seats = seatRepository.getAllSeats().seats
        val seatUIModels = seats.map { SeatUIModel(it) }
        seatsUIModel = SeatsUIModel(seatUIModels, reservationCount)

        view.showSeats(seatsUIModel)
    }

    override fun onSeatClicked(seatIndex: Int) {
        val result = seatsUIModel.toggleSeatSelection(seatIndex)
        when (result) {
            is SeatSelectionResult.Success -> {
                view.showSeats(seatsUIModel)
                updateTotalPriceDisplay()
                view.updateConfirmButton(false)
            }
            is SeatSelectionResult.MaxCapacityReached -> {
                view.showSeats(seatsUIModel)
                updateTotalPriceDisplay()
                view.updateConfirmButton(true)
            }
            is SeatSelectionResult.AlreadyMaxCapacityReached -> {
                view.showMessage(MAX_SELECTABLE_SEATS_EXCEEDED_MESSAGE)
                view.updateConfirmButton(true)
            }
            is SeatSelectionResult.Failure -> {
                view.showMessage(ERROR_SELECTING_SEAT_MESSAGE)
                view.updateConfirmButton(false)
            }
        }
    }

    override fun requestReservationResult() {
        runCatching {
            movieTicketRepository.updateReserveSeats(movieTicketId, seatsUIModel.selectedSeats())
        }.onSuccess {
            view.moveToReservationResult(movieTicketId)
        }.onFailure {
            view.showMessage(ERROR_MESSAGE.format(it.message ?: ""))
        }
    }

    override fun selectedSeats(): ArrayList<Int> {
        return seatsUIModel.selectedSeatIndices().toCollection(ArrayList())
    }

    private fun updateTotalPriceDisplay() {
        val total = seatsUIModel.totalPrice()
        view.showTotalPrice(total)
    }

    companion object {
        const val INVALID_MOVIE_TICKET_ID = -1
        const val INVALID_MOVIE_TICKET_ID_MESSAGE = "올바르지 않은 예매 ID입니다."
        const val ERROR_MESSAGE = "예매 정보를 불러오는데 실패했습니다. %s"
        const val MAX_SELECTABLE_SEATS_EXCEEDED_MESSAGE = "최대 선택 가능 좌석 수를 초과하였습니다."
        const val ERROR_SELECTING_SEAT_MESSAGE = "좌석 선택에 실패했습니다."
    }
}

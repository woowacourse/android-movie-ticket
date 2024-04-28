package woowacourse.movie.presentation.ui.seat

import woowacourse.movie.presentation.uimodel.SeatsUIModel


interface SeatContract {
    interface View {
        fun showMovieTitle(movieTitle: String)
        
        fun showSeats(seatsUIModel: SeatsUIModel)
        
        fun showTotalPrice(total: Int)
        
        fun updateConfirmButton(enabled: Boolean)
        
        fun moveToReservationResult(movieTicketId: Int)
        
        fun showMessage(message: String)
    }
    
    interface Presenter {
        fun loadScreeningInformation()
        
        fun onSeatClicked(seatIndex: Int)
    }
}

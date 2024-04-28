package woowacourse.movie.presentation.ui.seat

import woowacourse.movie.domain.model.Seat


interface SeatContract {
    interface View {
        fun showMovieTitle(movieTitle: String)
        
        fun showSeats(
            seat: List<Seat>
        )
        
        fun showMessage(message: String)
    }

    interface Presenter {
        fun loadScreeningInformation()
    }
}

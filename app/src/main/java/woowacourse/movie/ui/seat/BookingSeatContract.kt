package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.Headcount

interface BookingSeatContract {
    interface Presenter {
        fun fetchHeadcount(): Headcount

        fun fetchMovieTitle(): String

        fun updateTotalPrice()

        fun updateMovieTitle()

        fun updateSeat(seatTag: String)

        fun updateConfirmButton()

        fun completeBookingSeat()
    }

    interface View {
        fun getHeadcount(): Headcount?

        fun getMovieTitle(): String?

        fun setTotalPriceTextView(totalPrice: Int)

        fun setMovieTitleTextView(movieTitle: String)

        fun selectSeat(seatPosition: Pair<Int, Int>)

        fun unselectSeat(seatPosition: Pair<Int, Int>)

        fun setConfirmButton(isEnabled: Boolean)

        fun moveToBookingCompleteActivity(
            movieTitle: String,
            headcount: Headcount,
        )
    }
}

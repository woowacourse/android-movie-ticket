package woowacourse.movie.contract

import woowacourse.movie.domain.Seat

interface SeatSelectionContract {
    interface Presenter {
        fun presentSeats()

        fun presentTitle()

        fun presentPrice()

        fun onSeatClicked(seat: Seat)

        fun getSelectedSeats(): Set<Seat>
    }

    interface View {
        fun setSeats(
            seats: Set<Seat>,
            selectedSeats: Set<Seat>,
        )

        fun setTitle(title: String)

        fun setPrice(price: Int)

        fun setSeatIsSelected(
            seat: Seat,
            selected: Boolean,
        )
    }
}

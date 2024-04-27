package woowacourse.movie.seats.contract

import woowacourse.movie.seats.model.Seat

interface SeatsContract {
    interface View {
        fun setOnSelectSeat()

        val presenter: Presenter

        fun setSeatCellBackgroundColor(info: Seat)

        fun setMovieTitle(info: String)

        fun setTotalPrice(info: Int)

        fun setSeatsText(info: Seat)

        fun initSeats()

        fun initSeatsView(info: Seat)
    }

    interface Presenter {
        var seat: Seat

        fun setPriceInfo()

        fun setSeatsCellsBackgroundColorInfo()

        fun createSeat(
            rowIndex: Int,
            colIndex: Int,
        )

        fun setSeatsTextInfo()

        fun setSeatsViewInfo()

        fun setMovieTitleInfo()

        fun storeMovieId(movieId: Long)
    }
}

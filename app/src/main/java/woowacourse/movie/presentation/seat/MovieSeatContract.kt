package woowacourse.movie.presentation.seat

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieSeat
import woowacourse.movie.presentation.base.BaseContract

interface MovieSeatContract {
    interface View : BaseContract.View {
        fun onInitView(
            movie: Movie,
            seats: List<MovieSeat>,
        )

        fun onSeatUpdate(
            buttonIndex: Int,
            isSelected: Boolean,
        )

        fun onPriceUpdate(totalPrice: Int)

        fun onReservationButtonChanged(isEnable: Boolean)

        fun onReservationComplete(
            movieId: Long,
            movieScreenDateTimeId: Long,
            movieSeatIds: List<Long>,
            count: Int,
            totalPrice: Int,
        )
    }

    interface Presenter {
        fun loadSeats(
            movieId: Long,
            movieScreenDateTimeId: Long,
            countThreshold: Int,
        )

        fun selectSeat(
            buttonIndex: Int,
            movieSeat: MovieSeat,
            selectedState: Boolean,
        )

        fun reservation()
    }
}

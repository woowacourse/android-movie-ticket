package woowacourse.movie.presenter.booking.seat

import androidx.annotation.StringRes

interface BookingSeatContract {
    interface Presenter {
        fun loadInfos()

        fun toggleBackGroundColor(seatPosition: String)

        fun updatePrice()
    }

    interface View {
        fun showSeatView(
            seatPosition: String,
            isSelected: Boolean,
        )

        fun showErrorMessage(
            @StringRes messageResource: Int,
        )

        fun showFullScreen(
            movieTitle: String,
            totalPrice: String,
        )

        fun showTotalPrice(totalPrice: String)
    }
}

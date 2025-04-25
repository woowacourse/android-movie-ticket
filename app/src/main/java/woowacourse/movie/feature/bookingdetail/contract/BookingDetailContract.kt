package woowacourse.movie.feature.bookingdetail.contract

import woowacourse.movie.feature.model.BookingInfoUiModel
import woowacourse.movie.feature.model.MovieDateUiModel
import woowacourse.movie.feature.model.MovieUiModel

interface BookingDetailContract {
    interface View {
        fun setupDateView(dates: List<MovieDateUiModel>)

        fun setupTimeView(times: List<String>)

        fun updateView(bookingInfo: BookingInfoUiModel)

        fun updateTicketCount(count: Int)

        fun updateTimeSpinnerItems(times: List<String>)

        fun showBookingCompleteDialog(bookingInfo: BookingInfoUiModel)

        fun navigateToBack()

        fun navigateToBookingComplete(bookingInfo: BookingInfoUiModel)
    }

    interface Presenter {
        fun onCreateView(movieUiModel: MovieUiModel)

        fun onDateSelected(date: String)

        fun onTimeSelected(time: String)

        fun onTicketCountDecreased()

        fun onTicketCountIncreased()

        fun onBookingCompleteButtonClicked()

        fun onBackButtonClicked()

        fun onSaveInstanceState(): BookingInfoUiModel

        fun onRestoreInstanceState(existBookingInfo: BookingInfoUiModel)
    }
}

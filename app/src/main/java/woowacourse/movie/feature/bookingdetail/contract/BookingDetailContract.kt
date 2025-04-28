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

        fun navigateToBack()

        fun navigateToBookingSeat(bookingInfo: BookingInfoUiModel)
    }

    interface Presenter {
        fun prepareBookingInfo(movieUiModel: MovieUiModel)

        fun selectDate(date: String)

        fun selectTime(time: String)

        fun decreaseTicketCount()

        fun increaseTicketCount()

        fun confirmBookingInfo()

        fun onBackButtonClicked()

        fun saveBookingInfo(): BookingInfoUiModel

        fun loadBookingInfo(existBookingInfo: BookingInfoUiModel)
    }
}

package woowacourse.movie.presenter.booking

import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.ui.model.movie.MovieUiModel

interface BookingContract {
    interface Presenter {
        fun loadMovie(movieUiModel: MovieUiModel)

        fun loadScreeningDateTimes()

        fun updateScreeningDate(
            date: String,
            delimiter: String,
        )

        fun updateScreeningTime(time: String)

        fun loadHeadCount()

        fun increaseHeadCount()

        fun decreaseHeadCount()

        fun reserve()

        fun saveHeadCount(onReceived: (Int) -> Unit)

        fun saveScreeningDate(onReceived: (String) -> Unit)

        fun saveScreeningTime(onReceived: (String) -> Unit)

        fun restoreBookingResult(
            count: Int,
            date: String?,
            time: String?,
        )
    }

    interface View {
        fun showMovieInfo(movieUiModel: MovieUiModel)

        fun showErrorMessage(message: String)

        fun setScreeningDateSpinner(dates: List<String>)

        fun showScreeningDate(position: Int)

        fun setScreeningTimeSpinner(times: List<String>)

        fun showScreeningTime(position: Int)

        fun setScreeningTimeAdapter(times: List<String>)

        fun showHeadCount(count: String)

        fun setButtonClickListener()

        fun moveTo(bookingResultUiModel: BookingResultUiModel)
    }
}

package woowacourse.movie.presenter.booking

import woowacourse.movie.ui.model.movie.MovieUiModel

interface BookingContract {
    interface Presenter {
        fun loadMovie(movieUiModel: MovieUiModel)

        fun loadScreeningDateTimes()

        fun updateScreeningDate(date: String)

        fun updateScreeningTime(time: String)
    }

    interface View {
        fun showMovieInfo(movieUiModel: MovieUiModel)

        fun showErrorMessage(message: String)

        fun setScreeningDateSpinner(dates: List<String>)

        fun showScreeningDate(position: Int)

        fun setScreeningTimeSpinner(times: List<String>)

        fun showScreeningTime(position: Int)

        fun setScreeningTimeAdapter(times: List<String>)
    }
}

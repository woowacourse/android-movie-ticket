package woowacourse.movie.feature.bookingdetail.presenter

import woowacourse.movie.domain.model.BookingInfo
import woowacourse.movie.domain.model.DateType
import woowacourse.movie.domain.model.MovieDate
import woowacourse.movie.domain.model.MovieDates
import woowacourse.movie.domain.model.MovieTime
import woowacourse.movie.feature.bookingdetail.contract.BookingDetailContract
import woowacourse.movie.feature.bookingdetail.contract.BookingDetailContract.Presenter
import woowacourse.movie.feature.mapper.toDomain
import woowacourse.movie.feature.mapper.toUi
import woowacourse.movie.feature.model.BookingInfoUiModel
import woowacourse.movie.feature.model.MovieDateUiModel
import woowacourse.movie.feature.model.MovieTimeUiModel
import woowacourse.movie.feature.model.MovieUiModel

class BookingDetailPresenter(
    private val view: BookingDetailContract.View,
) : Presenter {
    private lateinit var bookingInfo: BookingInfo

    override fun onCreateView(movieUiModel: MovieUiModel) {
        bookingInfo = BookingInfo(movieUiModel.toDomain())

        val movieDates = MovieDates(bookingInfo.movie.startDate, bookingInfo.movie.endDate).value.map { it.toUi() }
        view.setupDateView(movieDates)

        val movieTimes = MovieTime.getMovieTimes(DateType.from(bookingInfo.date)).map { it.toUi().toString() }
        view.setupTimeView(movieTimes)

        view.updateView(bookingInfo.toUi())
    }

    override fun onDateSelected(date: String) {
        val movieDate: MovieDate = MovieDateUiModel.from(date).toDomain()
        bookingInfo.updateDate(movieDate)

        val movieTimes: List<String> = MovieTime.getMovieTimes(DateType.from(movieDate)).map { it.toUi().toString() }
        view.updateTimeSpinnerItems(movieTimes)
    }

    override fun onTimeSelected(time: String) {
        val movieTime: MovieTime = MovieTimeUiModel.from(time).toDomain()
        bookingInfo.updateMovieTime(movieTime)
    }

    override fun onTicketCountDecreased() {
        bookingInfo.decreaseTicketCount()
        view.updateTicketCount(bookingInfo.ticketCount.value)
    }

    override fun onTicketCountIncreased() {
        bookingInfo.increaseTicketCount()
        view.updateTicketCount(bookingInfo.ticketCount.value)
    }

    override fun onBookingCompleteButtonClicked() {
        view.showBookingCompleteDialog(bookingInfo.toUi())
    }

    override fun onBackButtonClicked() {
        view.navigateToBack()
    }

    override fun onSaveInstanceState(): BookingInfoUiModel = bookingInfo.toUi()

    override fun onRestoreInstanceState(existBookingInfo: BookingInfoUiModel) {
        bookingInfo = existBookingInfo.toDomain()
        view.updateView(existBookingInfo)
    }
}

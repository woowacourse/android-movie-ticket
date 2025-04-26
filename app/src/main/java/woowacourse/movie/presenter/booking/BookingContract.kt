package woowacourse.movie.presenter.booking

import woowacourse.movie.data.MovieStore
import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.booking.PeopleCount
import woowacourse.movie.view.movies.model.UiModel
import java.time.LocalDateTime

interface BookingContract {
    interface View {
        fun showMovieDetail(movie: UiModel.MovieUiModel)

        fun showPeopleCount(count: Int)

        fun showScreeningDate(screeningBookingDates: List<String>)

        fun showScreeningTime(screeningBookingTimes: List<String>)

        fun showToast()

        fun onClickIncrease()

        fun onClickDecrease()

        fun onClickBooking()

        fun onSelectDate(selectedDate: String)

        fun moveToBookingComplete(booking: Booking)

        fun restoreSavedState(savedCount: Int)
    }

    interface Presenter {
        fun loadMovieDetail(index: Int)

        fun loadPeopleCount()

        fun loadScreeningDate(
            startDate: String,
            endDate: String,
            now: LocalDateTime,
        )

        fun loadScreeningTime(
            selectedDate: String,
            now: LocalDateTime,
        )

        fun loadBooking(
            title: String,
            bookingDate: String,
            bookingTime: String,
            peopleCount: String,
        )

        fun decreasePeopleCount()

        fun increasePeopleCount()

        fun restorePeopleCount(savedCount: Int)
    }

    companion object PresenterFactory {
        fun providePresenter(view: View): Presenter {
            val movies = MovieStore()
            val peopleCount = PeopleCount()
            return BookingPresenter(view, movies, peopleCount)
        }
    }
}

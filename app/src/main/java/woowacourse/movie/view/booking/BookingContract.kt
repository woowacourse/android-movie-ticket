package woowacourse.movie.view.booking

import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.booking.PeopleCount
import woowacourse.movie.domain.model.booking.TicketType
import woowacourse.movie.domain.model.movies.DefaultMovieModel

interface BookingContract {
    interface View {
        fun showMovieDetail(
            title: String,
            posterResId: Int?,
            releaseStartDate: String,
            releaseEndDate: String,
            runningTime: Int,
        )

        fun showPeopleCount(count: Int)

        fun onClickIncrease()

        fun onClickDecrease()

        fun onClickBooking()

        fun moveToBookingComplete(booking: Booking)
    }

    interface Presenter {
        fun loadMovieDetail(index: Int)

        fun loadPeopleCount()

        fun decreasePeopleCount()

        fun increasePeopleCount()

        fun loadBooking(
            title: String,
            bookingDate: String,
            bookingTime: String,
            peopleCount: String,
            ticketType: TicketType,
        )
    }

    companion object PresenterFactory {
        fun providePresenter(view: View): Presenter {
            val movies = DefaultMovieModel()
            val peopleCount = PeopleCount()
            return BookingPresenter(view, movies, peopleCount)
        }
    }
}

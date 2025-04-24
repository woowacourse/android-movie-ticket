package woowacourse.movie.view.booking

import woowacourse.movie.domain.model.booking.PeopleCount
import woowacourse.movie.domain.model.movies.DefaultMovieModel
import woowacourse.movie.domain.model.movies.Movie

interface BookingContract {
    interface View {
        fun showMovieDetail(movie: Movie)

        fun showPeopleCount(count: Int)

        fun onClickIncrease()

        fun onClickDecrease()
    }

    interface Presenter {
        fun loadMovieDetail(index: Int)

        fun loadPeopleCount()

        fun decreasePeopleCount()

        fun increasePeopleCount()
    }

    companion object PresenterFactory {
        fun providePresenter(view: View): Presenter {
            val movies = DefaultMovieModel()
            val peopleCount = PeopleCount()
            return BookingPresenter(view, movies, peopleCount)
        }
    }
}

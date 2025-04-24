package woowacourse.movie.view.booking

import woowacourse.movie.domain.model.booking.PeopleCount
import woowacourse.movie.view.movies.MovieListContract

class BookingPresenter(
    private val view: BookingContract.View,
    private val movie: MovieListContract.MovieModel,
    private var count: PeopleCount,
) : BookingContract.Presenter {
    override fun loadMovieDetail(index: Int) {
        val movie = movie[index]
        view.showMovieDetail(movie)
    }

    override fun loadPeopleCount() {
        view.showPeopleCount(count.value)
    }

    override fun decreasePeopleCount() {
        count = count.decrease()
        view.showPeopleCount(count.value)
    }

    override fun increasePeopleCount() {
        count = count.increase()
        view.showPeopleCount(count.value)
    }
}

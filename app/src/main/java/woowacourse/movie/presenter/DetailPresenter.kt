package woowacourse.movie.presenter

import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket

class DetailPresenter(
    private val view: DetailContract.View,
) : DetailContract.Presenter {

    override fun loadMovie() {
        val movie = Movie(
            poster = R.drawable.poster,
            title = "해리 포터",
            date = "2024-04-12",
            runTime = "152분"
        )
        view.showMovie(movie)
    }

    override fun onClickedPlusButton(count: Int) {
        val count = if (count < 99) count + 1 else count
        view.updateCount(count)
    }

    override fun onClickedMinusCount(count: Int) {
        val count = if (count > 1) count - 1 else count
        view.updateCount(count)
    }

    override fun onClickedReservation(movie: Movie, count: Int) {
        val ticket = Ticket(count, movie)
        view.showTicket(ticket)
    }
}

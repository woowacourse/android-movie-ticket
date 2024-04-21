package woowacourse.movie.ui.main.adapter

import woowacourse.movie.model.MovieContent
import woowacourse.movie.model.data.MovieContents

class MovieContentListPresenter(
    private val view: MovieContentListContract.View,
    movieContents: MovieContents,
) :
    MovieContentListContract.Presenter {
    private val movieContents: List<MovieContent> by lazy { movieContents.findAll() }

    override fun count(): Int = movieContents.size

    override fun item(position: Int): MovieContent = movieContents[position]

    override fun itemId(position: Int): Long = item(position).id

    override fun setUpMovieContent(position: Int) {
        view.setUpMovieContentUi(item(position))
    }

    override fun moveMovieReservation(position: Int) {
        view.moveMovieReservationView(itemId(position))
    }
}

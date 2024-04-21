package woowacourse.movie.ui.adapter

import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.data.dto.MovieContent

class MovieContentListPresenter(private val view: MovieContentListContract.View) :
    MovieContentListContract.Presenter {
    private val movieContents: List<MovieContent> by lazy { MovieContentsImpl.findAll() }

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

package woowacourse.movie.presenter

import woowacourse.movie.conrtract.MovieContentListAdapterContract
import woowacourse.movie.dao.MovieContentsImpl
import woowacourse.movie.model.MovieContent

class MovieContentListAdapterPresenter(private val view: MovieContentListAdapterContract.View) :
    MovieContentListAdapterContract.Presenter {
    private val movieContents: List<MovieContent> by lazy { MovieContentsImpl.findAll() }

    override fun count(): Int = movieContents.size

    override fun item(position: Int): MovieContent = movieContents[position]

    override fun itemId(position: Int): Long = item(position).id

    override fun setUpMovieContent(position: Int) {
        view.setUpMovieContentUi(item(position))
    }

    override fun clickReservationButton(position: Int) {
        view.moveMovieReservationView(itemId(position))
    }
}

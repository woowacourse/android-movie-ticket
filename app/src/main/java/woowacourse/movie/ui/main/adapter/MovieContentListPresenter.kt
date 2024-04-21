package woowacourse.movie.ui.main.adapter

import woowacourse.movie.model.MovieContent

class MovieContentListPresenter(
    private val view: MovieContentListContract.View,
    private val movieContents: List<MovieContent>,
) :
    MovieContentListContract.Presenter {
    override fun count(): Int = movieContents.size

    override fun item(position: Int): MovieContent = movieContents[position]

    override fun itemId(position: Int): Long = item(position).id

    override fun setUpMovieContent(
        position: Int,
        movieViewHolder: MovieViewHolder,
    ) {
        this.view.setUpMovieContentUi(item(position), movieViewHolder)
    }

    override fun moveMovieReservation(position: Int) {
        view.moveMovieReservationView(itemId(position))
    }
}

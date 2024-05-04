package woowacourse.movie.home

import woowacourse.movie.db.MediaContents

class HomePresenter(
    private val view: HomeContract.View,
) : HomeContract.Presenter {
    init {
        loadMediaContents()
    }

    override fun loadMediaContents() {
        val mediaContents = MediaContents.obtainMediaContents().toList()
        view.showMediaContents(mediaContents)
    }

    override fun deliverMovieId(movieId: Int) {
        view.moveToReservationDetail(movieId)
    }
}

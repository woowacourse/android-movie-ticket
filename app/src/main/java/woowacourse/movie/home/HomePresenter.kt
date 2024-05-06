package woowacourse.movie.home

import woowacourse.movie.db.MediaContentsDB

class HomePresenter(
    private val view: HomeContract.View,
) : HomeContract.Presenter {
    init {
        loadMediaContents()
    }

    override fun loadMediaContents() {
        val mediaContents = MediaContentsDB.obtainMediaContents().toList()
        view.showMediaContents(mediaContents)
    }

    override fun deliverMovieId(movieId: Int) {
        view.moveToReservationDetail(movieId)
    }
}

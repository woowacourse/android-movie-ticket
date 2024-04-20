package woowacourse.movie.conrtract

import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.presenter.BasePresenter

interface MovieContentListContract {
    interface View {
        fun setUpMovieContentUi(movieContent: MovieContent)

        fun moveMovieReservationView(movieContentId: Long)
    }

    interface Presenter : BasePresenter {
        fun count(): Int

        fun item(position: Int): MovieContent

        fun itemId(position: Int): Long

        fun setUpMovieContent(position: Int)

        fun clickReservationButton(position: Int)
    }
}

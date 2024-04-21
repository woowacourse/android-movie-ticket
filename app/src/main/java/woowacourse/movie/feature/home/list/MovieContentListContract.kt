package woowacourse.movie.feature.home.list

import woowacourse.movie.base.BasePresenter
import woowacourse.movie.model.data.dto.MovieContent

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

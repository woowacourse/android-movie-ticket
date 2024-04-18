package woowacourse.movie.conrtract

import woowacourse.movie.model.MovieContent

interface MovieContentListAdapterContract {
    interface View {
        fun setUpMovieContentUi(movieContent: MovieContent)

        fun moveMovieReservationView(movieContentId: Long)
    }

    interface Presenter {
        fun count(): Int

        fun item(position: Int): MovieContent

        fun itemId(position: Int): Long

        fun setUpMovieContent(position: Int)

        fun clickReservationButton(position: Int)
    }
}

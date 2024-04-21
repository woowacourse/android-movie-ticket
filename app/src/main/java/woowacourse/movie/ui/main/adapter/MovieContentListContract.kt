package woowacourse.movie.ui.main.adapter

import woowacourse.movie.model.MovieContent

interface MovieContentListContract {
    interface View {
        fun setUpMovieContentUi(
            movieContent: MovieContent,
            movieViewHolder: MovieViewHolder,
        )

        fun moveMovieReservationView(movieContentId: Long)
    }

    interface Presenter {
        fun count(): Int

        fun item(position: Int): MovieContent

        fun itemId(position: Int): Long

        fun setUpMovieContent(
            position: Int,
            movieViewHolder: MovieViewHolder,
        )

        fun moveMovieReservation(position: Int)
    }
}

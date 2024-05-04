package woowacourse.movie.home

import woowacourse.movie.model.MediaContent

interface HomeContract {
    interface View {
        fun showMediaContents(mediaContents: List<MediaContent>)

        fun moveToReservationDetail(movieId: Int)
    }

    interface Presenter {
        fun loadMediaContents()

        fun deliverMovieId(movieId: Int)
    }
}

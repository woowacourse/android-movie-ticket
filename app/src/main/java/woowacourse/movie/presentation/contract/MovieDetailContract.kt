package woowacourse.movie.presentation.contract

import woowacourse.movie.domain.model.MovieTicket

interface MovieDetailContract {
    interface View {
        fun showMovieDetail(
            posterImageId: Int,
            title: String,
            screeningDate: String,
            runningTime: Int,
            summary: String,
        )

        fun showReservationCount(count: Int)

        fun moveToReservationResult()
    }

    interface Presenter {
        val movieTicket: MovieTicket

        fun minusReservationCount()

        fun plusReservationCount()

        fun initReservationCount(count: Int)
    }
}

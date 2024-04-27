package woowacourse.movie.presenter.contract

import woowacourse.movie.model.screening.Screening

interface MovieListContract {
    interface View {
        fun initializeScreeningList(
            screenings: List<Screening>,
            advertisementDrawableId: Int,
        )

        fun navigateToTicketing(screeningId: Long)
    }

    interface Presenter {
        fun startReservation(screeningId: Long)
    }
}

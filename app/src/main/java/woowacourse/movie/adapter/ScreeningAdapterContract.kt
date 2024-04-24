package woowacourse.movie.adapter

import woowacourse.movie.model.screening.Screening

interface ScreeningAdapterContract {
    interface View {
        fun notifyItemClicked(position: Int)
    }
    interface Model {
        fun setScreenings(screenings: List<Screening>)
    }
}

package woowacourse.movie.contract

import woowacourse.movie.model.Theater

interface SeatSelectionContract {
    interface View{
        fun displayTheater(theater: Theater)
    }
    interface Presenter{
        fun loadTheater()
    }
}

package woowacourse.movie.seat

import woowacourse.movie.domain.Point

interface SeatContract {
    interface View

    interface Presenter {
        fun getPoint(
            row: Int,
            col: Int,
        ): Point
    }
}

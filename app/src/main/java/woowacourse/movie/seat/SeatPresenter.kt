package woowacourse.movie.seat

import woowacourse.movie.domain.Point

class SeatPresenter : SeatContract.Presenter {
    override fun getPoint(
        row: Int,
        col: Int,
    ): Point {
        return Point(row, col)
    }
}

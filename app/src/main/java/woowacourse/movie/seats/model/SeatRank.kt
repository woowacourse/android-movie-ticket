package woowacourse.movie.seats.model

import android.graphics.Color

enum class SeatRank(val price: Int, val rowRange: IntRange, val textColor: Int) {
    B(10_000, (0..1), Color.MAGENTA),
    A(12_000, (4..4), Color.GREEN),
    S(15_000, (2..3), Color.BLUE),
    ;

    companion object {
        fun of(rowIndex: Int): SeatRank = entries.first { rowIndex in it.rowRange }
    }
}

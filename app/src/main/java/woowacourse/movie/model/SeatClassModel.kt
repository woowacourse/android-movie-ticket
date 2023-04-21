package woowacourse.movie.model

import androidx.annotation.ColorRes
import woowacourse.movie.R

enum class SeatClassModel(@ColorRes val textColorId: Int) {
    B(R.color.seat_color_b),
    S(R.color.seat_color_s),
    A(R.color.seat_color_a),
    ;

    companion object {
        fun getColorId(row: Int): Int {
            return when (row) {
                in 1..2 -> B.textColorId
                in 3..4 -> S.textColorId
                5 -> A.textColorId
                else -> throw IllegalArgumentException()
            }
        }
    }
}

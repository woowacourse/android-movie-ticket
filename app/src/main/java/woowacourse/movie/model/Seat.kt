package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Seat(
    val label: String,
) : Parcelable {
    val price: Int
        get() =
            when (label.first().uppercaseChar()) {
                'A', 'B' -> 10000
                'C', 'D' -> 15000
                'E' -> 12000
                else -> throw IllegalArgumentException("유효하지 않은 좌석입니다.")
            }
}

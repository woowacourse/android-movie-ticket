package woowacourse.movie.model.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class RowUI(
    val x: Int
) : Parcelable {
    override fun toString(): String = (START_SEAT_POSITION + x).toString()

    companion object {
        private const val START_SEAT_POSITION = 'A'
    }
}

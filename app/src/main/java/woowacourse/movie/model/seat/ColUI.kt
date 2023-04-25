package woowacourse.movie.model.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class ColUI(
    val y: Int
) : Parcelable {
    override fun toString(): String = (y + COLUMN_UI_CONDITION).toString()

    companion object {
        private const val COLUMN_UI_CONDITION = 1
    }
}

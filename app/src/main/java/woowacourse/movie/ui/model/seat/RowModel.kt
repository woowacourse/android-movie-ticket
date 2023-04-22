package woowacourse.movie.ui.model.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

internal fun Int.mapToRowModel(): RowModel = RowModel.of(this)

@Parcelize
data class RowModel(val value: Int, val letter: Char) : Parcelable {
    companion object {
        private const val ALPHABET_A_NUMBER = 65

        fun createRows(size: Int): List<RowModel> =
            (ALPHABET_A_NUMBER until ALPHABET_A_NUMBER + size).map { RowModel(it - ALPHABET_A_NUMBER + 1, it.toChar()) }

        fun of(number: Int): RowModel = RowModel(number, (ALPHABET_A_NUMBER + number - 1).toChar())
    }
}

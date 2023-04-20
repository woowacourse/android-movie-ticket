package woowacourse.movie.ui.model.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

fun mapToRowModel(row: Int): RowModel = RowModel.of(row)

@Parcelize
class RowModel private constructor(val value: Int, val letter: Char) : Parcelable {
    companion object {
        private const val ALPHABET_A_NUMBER = 65

        fun createRows(size: Int): List<RowModel> =
            (ALPHABET_A_NUMBER until ALPHABET_A_NUMBER + size).map { RowModel(it - ALPHABET_A_NUMBER + 1, it.toChar()) }

        fun of(number: Int): RowModel = RowModel(number, (ALPHABET_A_NUMBER + number - 1).toChar())
    }
}

package woowacourse.movie.ui.model.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

internal fun Int.mapToColumnModel(): ColumnModel = ColumnModel.of(this)

@Parcelize
data class ColumnModel(val value: Int) : Parcelable {
    companion object {
        fun createColumns(size: Int): List<ColumnModel> = (1..size).map(::ColumnModel)

        fun of(number: Int): ColumnModel = ColumnModel(number)
    }
}

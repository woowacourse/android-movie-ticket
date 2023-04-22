package woowacourse.movie.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.grade.Position

@Parcelize
class PositionUIModel(val rowIndex: Int, val columnIndex: Int) : Parcelable {

    companion object {
        fun Position.toPositionUIModel() = PositionUIModel(this.rowIndex, this.columnIndex)

        fun PositionUIModel.toPosition() = Position.from(this.rowIndex, this.columnIndex)
    }
}

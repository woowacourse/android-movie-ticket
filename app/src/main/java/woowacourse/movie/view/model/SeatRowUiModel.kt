package woowacourse.movie.view.model

import java.io.Serializable

enum class SeatRowUiModel : UiModel, Serializable {
    A, B, C, D, E;

    companion object {
        fun find(alphabet: String): SeatRowUiModel {
            return valueOf(alphabet)
        }

        fun toNumber(seatRowUiModel: SeatRowUiModel): Int {
            return seatRowUiModel.name[0] - 'A' + 1
        }

        fun numberToSeatRow(rowNumber: Int): SeatRowUiModel {
            val alphabet = (rowNumber + 'A'.code - 1).toChar()
            return valueOf(alphabet.toString())
        }
    }
}

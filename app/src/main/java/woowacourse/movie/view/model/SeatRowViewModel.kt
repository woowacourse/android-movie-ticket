package woowacourse.movie.view.model

import java.io.Serializable

enum class SeatRowViewModel : ViewModel, Serializable {
    A, B, C, D, E;

    companion object {
        fun find(alphabet: String): SeatRowViewModel {
            return valueOf(alphabet)
        }

        fun toNumber(seatRowViewModel: SeatRowViewModel): Int {
            return seatRowViewModel.name[0] - 'A' + 1
        }

        fun numberToSeatRow(rowNumber: Int): SeatRowViewModel {
            val alphabet = (rowNumber + 'A'.code - 1).toChar()
            return valueOf(alphabet.toString())
        }
    }
}

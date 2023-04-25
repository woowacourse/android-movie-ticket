package woowacourse.movie.model

enum class RowSeat {
    A, B, C, D, E;

    companion object {
        fun of(index: Int): String {
            return RowSeat.values()[index].name
        }
    }
}

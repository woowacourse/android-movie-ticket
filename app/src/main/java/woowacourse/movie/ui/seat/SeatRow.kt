package woowacourse.movie.ui.seat

enum class SeatRow {
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;

    companion object {
        fun find(ordinal: Int): SeatRow {
            return values().find { it.ordinal == ordinal }
                ?: throw IllegalArgumentException("알파벳은 총 26개입니다. 현재 ordinal: $ordinal")
        }
    }
}

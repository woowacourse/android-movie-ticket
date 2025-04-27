package woowacourse.movie.domain.model

enum class SeatRate {
    S,
    A,
    B,
    NONE,
    ;

    companion object {
        fun of(rate: String): SeatRate =
            when (rate) {
                "s" -> S
                "a" -> A
                "b" -> B
                else -> NONE
            }
    }
}

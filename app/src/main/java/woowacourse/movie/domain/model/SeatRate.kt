package woowacourse.movie.domain.model

enum class SeatRate {
    S,
    A,
    B,
    ;

    companion object {
        fun of(rate: String): SeatRate? =
            when (rate) {
                "s" -> S
                "a" -> A
                "b" -> B
                else -> null
            }
    }
}

package woowacourse.movie.domain.model.seat

import java.io.Serializable

enum class SeatRate : Serializable {
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

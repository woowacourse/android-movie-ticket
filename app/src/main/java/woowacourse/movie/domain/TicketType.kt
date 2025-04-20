package woowacourse.movie.domain

import java.io.Serializable

enum class TicketType(val price: Int) : Serializable {
    DEFAULT(13_000),
}

package woowacourse.movie.domain.model

import java.io.Serializable

enum class TicketType(val price: Int) : Serializable {
    DEFAULT(13_000),
}

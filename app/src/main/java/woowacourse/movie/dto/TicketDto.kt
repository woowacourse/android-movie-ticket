package woowacourse.movie.dto

import java.io.Serializable

data class TicketDto(
    val numberOfPeople: Int = MIN_BOOKER_NUMBER,
) : Serializable {
    companion object {
        const val MIN_BOOKER_NUMBER = 1
    }
}

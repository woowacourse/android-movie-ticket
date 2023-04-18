package woowacourse.movie.domain.price

import java.io.Serializable

class TicketCount(value: Int) : Serializable {

    init {
        validateNegaticeInitialValue(value)
    }

    private fun validateNegaticeInitialValue(value: Int) {
        require(value >= 1) { INITIAL_NEGATIVE_TICKET_COUNT_ERROR }
    }

    var value: Int = value
        set(value) {
            field = 1
            if (value > 1) field = value
        }

    companion object {
        private const val INITIAL_NEGATIVE_TICKET_COUNT_ERROR = "TicketCount 의 초기값으로 1이하를 지정하였습니다."
    }
}

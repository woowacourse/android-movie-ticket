package woowacourse.movie.domain.seat

import woowacourse.movie.domain.ticket.Price

sealed class SelectResult {
    class Selection(val seatPrice: Price) : SelectResult()
    class Deselection(val seatPrice: Price) : SelectResult()
    class WrongInput : SelectResult()
    class MaxSelection : SelectResult()
}

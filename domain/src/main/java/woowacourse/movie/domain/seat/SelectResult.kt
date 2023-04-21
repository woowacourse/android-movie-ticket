package woowacourse.movie.domain.seat

import woowacourse.movie.domain.ticket.Price

sealed class SelectResult {
    sealed class Success(val seatPrice: Price) : SelectResult() {
        class Selection(seatPrice: Price) : Success(seatPrice)
        class Deselection(seatPrice: Price) : Success(seatPrice)
    }

    object WrongInput : SelectResult()
    object MaxSelection : SelectResult()
}

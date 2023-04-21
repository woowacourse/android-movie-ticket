package woowacourse.movie.domain.system

import woowacourse.movie.domain.price.Price

sealed class SelectResult {
    sealed class Success(val seatPrice: Price) : SelectResult() {
        class Selection(seatPrice: Price, val isSelectAll: Boolean) : Success(seatPrice)
        class Deselection(seatPrice: Price) : Success(seatPrice)
    }

    object WrongInput : SelectResult()
    object MaxSelection : SelectResult()
}

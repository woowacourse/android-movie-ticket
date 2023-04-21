package woowacourse.movie.domain.ticket

import woowacourse.movie.domain.seat.SelectResult
import java.time.LocalDateTime

class PriceSystem(
    private val calculator: PriceCalculator,
    private val playingDateTime: LocalDateTime,
) {
    fun getCurrentPrice(existingPrice: Price, result: SelectResult): Price {
        return when (result) {
            is SelectResult.Success.Deselection -> existingPrice - calculator.calculate(
                result.seatPrice,
                playingDateTime,
            )
            is SelectResult.Success.Selection -> existingPrice + calculator.calculate(
                result.seatPrice,
                playingDateTime,
            )
            is SelectResult.MaxSelection -> existingPrice
            is SelectResult.WrongInput -> existingPrice
        }
    }
}

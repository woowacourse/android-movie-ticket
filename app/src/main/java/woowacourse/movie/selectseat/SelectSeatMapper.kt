package woowacourse.movie.selectseat

import woowacourse.movie.model.Seat
import woowacourse.movie.model.SeatRate
import woowacourse.movie.selectseat.uimodel.RateColor
import woowacourse.movie.selectseat.uimodel.SeatUiModel

fun List<Seat>.toSeatsUiModel() = this.map { SeatUiModel(it.row, it.col, it.rate) }

fun List<SeatUiModel>.toSeats() =
    this.map {
        Seat(
            it.rateColor.toRate(),
            it.showPosition[0].uppercaseChar() - 'A' + 1,
            it.showPosition[1].toString().toInt(),
        )
    }

private fun RateColor.toRate() =
    when (this) {
        RateColor.GREEN -> SeatRate.S
        RateColor.BLUE -> SeatRate.A
        RateColor.PURPLE -> SeatRate.B
    }

package woowacourse.movie.model

import domain.seat.SeatColumn
import domain.seat.SeatRow
import domain.seat.SeatState
import java.io.Serializable

sealed class ScreeningPlace(val seats: Map<ScreeningSeatInfo, SeatState>) : Serializable {

    object Default : ScreeningPlace(
        seats = SeatRow.values()
            .flatMap { row ->
                SeatColumn.values().map { column ->
                    ScreeningSeatInfo(row, column)
                }
            }.associateWith {
                SeatState.AVAILABLE
            }
    )
}

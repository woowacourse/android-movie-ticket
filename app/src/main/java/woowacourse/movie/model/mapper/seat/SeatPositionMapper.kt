package woowacourse.movie.model.mapper.seat

import com.woowacourse.movie.domain.seat.SeatPosition
import woowacourse.movie.model.seat.SeatPositionUI

fun SeatPosition.toSeatPositionUI(): SeatPositionUI = SeatPositionUI(row.toRowUI(), col.toColUI())

fun SeatPositionUI.toSeatPosition(): SeatPosition = SeatPosition(row.toRow(), col.toCol())

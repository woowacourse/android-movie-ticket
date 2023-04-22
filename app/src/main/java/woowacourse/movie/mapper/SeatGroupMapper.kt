package woowacourse.movie.mapper

import com.woowacourse.domain.seat.SeatGroup
import woowacourse.movie.model.SeatGroupModel

fun SeatGroup.toPresentation(): SeatGroupModel = SeatGroupModel(seats.map { it.toPresentation() })

fun SeatGroupModel.toDomain(): SeatGroup = SeatGroup(seats.map { it.toDomain() })

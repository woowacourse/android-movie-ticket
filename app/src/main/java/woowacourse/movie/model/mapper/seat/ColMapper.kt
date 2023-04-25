package woowacourse.movie.model.mapper.seat

import com.woowacourse.movie.domain.seat.Col
import woowacourse.movie.model.seat.ColUI

fun Col.toColUI(): ColUI = ColUI(y)

fun ColUI.toCol(): Col = Col(y)

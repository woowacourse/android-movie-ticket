package woowacourse.movie.model.mapper.seat

import com.woowacourse.movie.domain.seat.Row
import woowacourse.movie.model.seat.RowUI

fun Row.toRowUI(): RowUI = RowUI(x)

fun RowUI.toRow(): Row = Row(x)

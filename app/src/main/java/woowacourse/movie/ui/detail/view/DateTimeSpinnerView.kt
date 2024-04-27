package woowacourse.movie.ui.detail.view

import woowacourse.movie.domain.model.DateRange

interface DateTimeSpinnerView {
    fun show(dateRange: DateRange)

    fun restoreDatePosition(position: Int)

    fun restoreTimePosition(position: Int)

    fun selectedDatePosition(): Int

    fun selectedTimePosition(): Int
}

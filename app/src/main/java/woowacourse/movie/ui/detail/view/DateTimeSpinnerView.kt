package woowacourse.movie.ui.detail.view

import woowacourse.movie.domain.model.DateRange
import woowacourse.movie.domain.model.ScreenTimePolicy

interface DateTimeSpinnerView {
    fun show(
        dateRange: DateRange,
        screenTimePolicy: ScreenTimePolicy,
        selectDateListener: SelectDateListener,
        selectTimeListener: SelectTimeListener,
    )

    fun restoreDatePosition(position: Int)

    fun restoreTimePosition(position: Int)

    fun selectedDatePosition(): Int

    fun selectedTimePosition(): Int
}

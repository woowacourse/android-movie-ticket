package woowacourse.movie.ui.detail.view

import woowacourse.movie.domain.model.DateRange
import woowacourse.movie.domain.model.ScreenTimePolicy
import woowacourse.movie.ui.detail.ScreenDetailContract

interface DateTimeSpinnerView {
    fun show(
        dateRange: DateRange,
        screenTimePolicy: ScreenTimePolicy,
        presenter: ScreenDetailContract.Presenter,
    )

    fun restoreDatePosition(position: Int)

    fun restoreTimePosition(position: Int)

    fun selectedDatePosition(): Int

    fun selectedTimePosition(): Int
}

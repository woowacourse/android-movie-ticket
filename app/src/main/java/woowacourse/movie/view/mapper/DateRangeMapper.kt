package woowacourse.movie.view.mapper

import woowacourse.movie.domain.DateRange
import woowacourse.movie.view.DateRangeView

object DateRangeMapper : Mapper<DateRange, DateRangeView> {
    override fun DateRange.toView(): DateRangeView {
        return DateRangeView(
            startDate,
            endDate
        )
    }

    override fun DateRangeView.toDomain(): DateRange {
        return DateRange(
            startDate,
            endDate
        )
    }
}

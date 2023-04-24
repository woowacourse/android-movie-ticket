package woowacourse.movie.view.mapper

import woowacourse.movie.domain.DateRange
import woowacourse.movie.view.data.DateRangeViewData

object DateRangeMapper : Mapper<DateRange, DateRangeViewData> {
    override fun DateRange.toView(): DateRangeViewData {
        return DateRangeViewData(
            startDate,
            endDate
        )
    }

    override fun DateRangeViewData.toDomain(): DateRange {
        return DateRange(
            startDate,
            endDate
        )
    }
}

package woowacourse.movie.view.mapper

import woowacourse.movie.domain.ReservationDetail
import woowacourse.movie.view.data.ReservationDetailViewData

object ReservationDetailMapper : Mapper<ReservationDetail, ReservationDetailViewData> {
    override fun ReservationDetail.toView(): ReservationDetailViewData {
        return ReservationDetailViewData(
            date,
            peopleCount
        )
    }

    override fun ReservationDetailViewData.toDomain(): ReservationDetail {
        return ReservationDetail(
            date,
            peopleCount
        )
    }
}

package woowacourse.movie.view.mapper

import woowacourse.movie.domain.Price
import woowacourse.movie.domain.ReservationDetail
import woowacourse.movie.view.ReservationDetailViewData

object ReservationDetailMapper : Mapper<ReservationDetail, ReservationDetailViewData> {
    override fun ReservationDetail.toView(): ReservationDetailViewData {
        return ReservationDetailViewData(
            date,
            peopleCount,
            price.value
        )
    }

    override fun ReservationDetailViewData.toDomain(): ReservationDetail {
        return ReservationDetail(
            date,
            peopleCount,
            Price(price)
        )
    }
}

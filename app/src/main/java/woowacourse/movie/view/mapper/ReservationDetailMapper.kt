package woowacourse.movie.view.mapper

import woowacourse.movie.domain.Price
import woowacourse.movie.domain.ReservationDetail
import woowacourse.movie.view.ReservationDetailView

object ReservationDetailMapper : Mapper<ReservationDetail, ReservationDetailView> {
    override fun ReservationDetail.toView(): ReservationDetailView {
        return ReservationDetailView(
            date,
            peopleCount,
            price.value
        )
    }

    override fun ReservationDetailView.toDomain(): ReservationDetail {
        return ReservationDetail(
            date,
            peopleCount,
            Price(price)
        )
    }
}

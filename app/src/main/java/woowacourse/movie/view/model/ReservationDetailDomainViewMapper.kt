package woowacourse.movie.view.model

import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.discountPolicy.DisCountPolicies
import woowacourse.movie.domain.discountPolicy.MovieDay
import woowacourse.movie.domain.discountPolicy.OffTime

class ReservationDetailDomainViewMapper : DomainViewMapper<Ticket, ReservationDetailViewModel> {
    override fun toDomain(viewModel: ReservationDetailViewModel): Ticket {
        return Ticket(
            date = viewModel.date,
            peopleCount = viewModel.peopleCount,
            disCountPolicies = DisCountPolicies(listOf(MovieDay(), OffTime()))
        )
    }

    override fun toView(domainModel: Ticket): ReservationDetailViewModel {
        return ReservationDetailViewModel(
            date = domainModel.date,
            peopleCount = domainModel.peopleCount,
            price = domainModel.totalPrice.value
        )
    }
}

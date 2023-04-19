package woowacourse.movie.view.model

import domain.Ticket
import domain.discountPolicy.DisCountPolicies
import domain.discountPolicy.MovieDay
import domain.discountPolicy.OffTime

class ReservationDetailDomainViewMapper : DomainViewMapper<domain.Ticket, ReservationDetailViewModel> {
    override fun toDomain(viewModel: ReservationDetailViewModel): domain.Ticket {
        return domain.Ticket(
            date = viewModel.date,
            peopleCount = viewModel.peopleCount,
            disCountPolicies = domain.discountPolicy.DisCountPolicies(
                listOf(
                    domain.discountPolicy.MovieDay(),
                    domain.discountPolicy.OffTime()
                )
            )
        )
    }

    override fun toView(domainModel: domain.Ticket): ReservationDetailViewModel {
        return ReservationDetailViewModel(
            date = domainModel.date,
            peopleCount = domainModel.peopleCount,
            price = domainModel.totalPrice.value
        )
    }
}

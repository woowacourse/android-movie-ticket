package woowacourse.movie.dto

import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.discountPolicy.DisCountPolicies
import woowacourse.movie.domain.discountPolicy.MovieDay
import woowacourse.movie.domain.discountPolicy.OffTime

class ReservationDetailDtoConverter : DtoConverter<Ticket, ReservationDetailDto> {
    override fun convertDtoToModel(dto: ReservationDetailDto): Ticket {
        return Ticket(
            date = dto.date,
            peopleCount = dto.peopleCount,
            disCountPolicies = DisCountPolicies(listOf(MovieDay(), OffTime()))
        )
    }

    override fun convertModelToDto(model: Ticket): ReservationDetailDto {
        return ReservationDetailDto(
            date = model.date,
            peopleCount = model.peopleCount,
            price = model.totalPrice.value
        )
    }
}

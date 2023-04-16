package woowacourse.movie.dto

import woowacourse.movie.domain.ReservationDetail
import woowacourse.movie.domain.discountPolicy.Discount
import woowacourse.movie.domain.discountPolicy.MovieDay
import woowacourse.movie.domain.discountPolicy.OffTime

class ReservationDetailDtoConverter : DtoConverter<ReservationDetail, ReservationDetailDto> {
    override fun convertDtoToModel(dto: ReservationDetailDto): ReservationDetail {
        return ReservationDetail(
            date = dto.date,
            peopleCount = dto.peopleCount,
            discount = Discount(listOf(MovieDay, OffTime))
        )
    }

    override fun convertModelToDto(model: ReservationDetail): ReservationDetailDto {
        return ReservationDetailDto(
            date = model.date,
            peopleCount = model.peopleCount,
            price = model.totalPrice.value
        )
    }
}

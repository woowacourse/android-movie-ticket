package woowacourse.movie.dto

import java.io.Serializable

data class ReservationDto(val movie: MovieDto, val detail: ReservationDetailDto) :
    Dto,
    Serializable {
    companion object {
        const val RESERVATION_KEY_VALUE = "reservation"
    }
}

package woowacourse.movie.dto

import java.io.Serializable

data class ReservationDto(val movie: MovieDto, val detail: ReservationDetailDto) : Serializable

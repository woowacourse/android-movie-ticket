package woowacourse.movie.dto

import java.io.Serializable

data class SeatsDto(val seats: List<SeatDto> = emptyList()) : Serializable

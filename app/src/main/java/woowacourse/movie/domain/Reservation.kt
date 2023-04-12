package woowacourse.movie.domain

import java.io.Serializable

data class Reservation(val movie: Movie, val detail: ReservationDetail) : Serializable

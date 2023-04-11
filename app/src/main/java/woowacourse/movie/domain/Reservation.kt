package woowacourse.movie.domain

import java.io.Serializable
import java.util.Date

data class Reservation(val date: Date, val peopleCount: Int, val movie: Movie, val price: Int) :
    Serializable

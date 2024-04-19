package woowacourse.movie.model.movieInfo

import java.io.Serializable
import java.time.LocalDate

class MovieDate(private val date: LocalDate) : Serializable {
    override fun toString(): String = "${date.year}.${date.monthValue}.${date.dayOfMonth}"
}

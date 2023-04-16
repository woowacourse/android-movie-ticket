package woowacourse.movie.domain.movieinfo

import java.io.Serializable
import java.time.LocalDate

class MovieDate(val date: LocalDate) : Serializable {

    companion object {
        fun of(date: String): MovieDate = MovieDate(LocalDate.parse(date))
    }
}

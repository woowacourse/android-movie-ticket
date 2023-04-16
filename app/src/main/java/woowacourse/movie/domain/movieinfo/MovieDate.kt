package woowacourse.movie.domain.movieinfo

import java.time.LocalDate

class MovieDate(val date: LocalDate) {

    companion object {
        fun of(date: String): MovieDate = MovieDate(LocalDate.parse(date))
    }
}

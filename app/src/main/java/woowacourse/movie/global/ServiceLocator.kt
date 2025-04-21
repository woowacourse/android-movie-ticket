package woowacourse.movie.global

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Movies
import java.time.LocalDate
import java.time.LocalDateTime

object ServiceLocator {
    var movies: List<Movie> = Movies.dummy
    var today: LocalDate = LocalDate.now()
    var now: LocalDateTime = LocalDateTime.now()
}

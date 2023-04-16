package woowacourse.movie.domain.movieinfo

import java.io.Serializable
import java.time.LocalTime

class MovieTime(val time: LocalTime) : Serializable {

    companion object {
        fun of(time: String): MovieTime = MovieTime(LocalTime.parse(time))
    }
}

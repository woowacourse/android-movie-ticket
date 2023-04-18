package domain.movieinfo

import java.time.LocalTime

class MovieTime(val time: LocalTime) {

    companion object {
        fun of(time: String): MovieTime = MovieTime(LocalTime.parse(time))
    }
}

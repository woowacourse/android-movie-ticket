package woowacourse.movie.domain

import java.time.LocalDateTime

data class ScreeningInfoOfMovie(val screeningDateTime: LocalDateTime, val movieHouse: MovieHouse) {

    fun canBeReserved(seatPoints: Set<Point>): Boolean =
        seatPoints.all { movieHouse.containsSeatOn(it) }

    fun getInitFee(seatPoint: Point): Money = movieHouse.getFee(seatPoint)
}

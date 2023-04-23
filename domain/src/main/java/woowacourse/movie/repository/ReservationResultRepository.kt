package woowacourse.movie.repository

import woowacourse.movie.domain.ReservationResult

object ReservationResultRepository {

    private var nextId = 1L
    private val reservationResults: MutableMap<Long, ReservationResult> = mutableMapOf()

    fun save(reservationResult: ReservationResult): Long {
        reservationResults[nextId] = reservationResult
        return nextId++
    }
}

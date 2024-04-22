package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.IScreen
import woowacourse.movie.domain.model.Reservation

interface ReservationRepository {
    fun save(
        screen: IScreen,
        count: Int,
    ): Result<Int>

    fun findById(id: Int): Result<Reservation>
}

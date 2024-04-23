package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.SeatBoard

interface ScreenRepository {
    fun load(): List<Screen>

    fun loadSeatBoard(id: Int): Result<SeatBoard>

    fun findByScreenId(id: Int): Result<Screen>
}

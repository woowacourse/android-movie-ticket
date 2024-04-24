package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.ScreenViewType
import woowacourse.movie.domain.model.SeatBoard

interface ScreenRepository {
    fun load(): List<ScreenViewType>

    fun loadSeatBoard(id: Int): Result<SeatBoard>

    fun findByScreenId(id: Int): Result<Screen>
}

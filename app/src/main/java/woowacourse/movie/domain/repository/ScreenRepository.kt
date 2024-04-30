package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seats

interface ScreenRepository {
    fun load(): List<Screen>

    fun findById(id: Int): Result<Screen>

    fun seats(screenId: Int): Seats
}

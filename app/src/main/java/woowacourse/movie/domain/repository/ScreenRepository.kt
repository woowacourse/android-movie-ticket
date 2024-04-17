package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Screen

interface ScreenRepository {
    fun load(): List<Screen>

    fun findById(id: Int): Result<Screen>
}

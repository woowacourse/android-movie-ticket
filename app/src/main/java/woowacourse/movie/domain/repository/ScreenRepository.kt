package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Screen

interface ScreenRepository {
    fun load(): List<Screen>

    fun findByScreenId(id: Int): Result<Screen>
}

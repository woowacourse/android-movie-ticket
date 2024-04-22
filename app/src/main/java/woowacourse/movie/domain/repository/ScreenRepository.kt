package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.IScreen

interface ScreenRepository {
    fun load(): List<IScreen>

    fun findById(id: Int): Result<IScreen>
}

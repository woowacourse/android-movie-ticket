package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Screen2

interface ScreenRepository {
    fun load(): List<Screen>

    fun findById(id: Int): Result<Screen>
}

interface ScreenRepository2 {
    fun load(): List<Screen2>

    fun findById(id: Int): Result<Screen2>
}

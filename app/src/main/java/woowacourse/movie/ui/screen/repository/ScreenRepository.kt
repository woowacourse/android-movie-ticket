package woowacourse.movie.ui.screen.repository

import woowacourse.movie.model.Screen

interface ScreenRepository {
    fun load(): List<Screen>

    fun findById(id: Int): Result<Screen>
}

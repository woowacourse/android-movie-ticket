package woowacourse.movie.ui.screen

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.repository.ScreenRepository

class ScreenViewModel(private val repository: ScreenRepository) {
    fun load(): List<Screen> = repository.load()
}

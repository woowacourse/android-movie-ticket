package woowacourse.movie.ui.screen

import woowacourse.movie.model.Screen
import woowacourse.movie.ui.screen.repository.ScreenRepository

class ScreenViewModel(private val repository: ScreenRepository) {
    fun load(): List<Screen> = repository.load()
}

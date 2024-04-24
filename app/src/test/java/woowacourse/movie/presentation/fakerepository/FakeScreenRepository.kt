package woowacourse.movie.presentation.fakerepository

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.SeatBoard
import woowacourse.movie.domain.repository.ScreenRepository

class FakeScreenRepository(private val screens: List<Screen>) : ScreenRepository {
    override fun load(): List<Screen> {
        return screens
    }

    override fun loadSeatBoard(id: Int): Result<SeatBoard> {
        TODO("Not yet implemented")
    }

    override fun findByScreenId(id: Int): Result<Screen> {
        return runCatching {
            screens.find { it.id == id } ?: throw NoSuchElementException()
        }
    }
}

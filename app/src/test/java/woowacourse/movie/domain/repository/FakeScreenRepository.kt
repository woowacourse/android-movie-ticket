package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Screen

class FakeScreenRepository : ScreenRepository {
    private val screens = listOf(fakeScreen1, fakeScreen2, fakeScreen3)

    override fun load(): List<Screen> {
        return screens
    }

    override fun findById(id: Int): Result<Screen> = runCatching { screens.find { it.id == id } ?: throw NoSuchElementException() }

    companion object {
        val fakeScreen1 = Screen(1, FakeMovieRepository.fakeMovie1, "1", 1)
        val fakeScreen2 = Screen(2, FakeMovieRepository.fakeMovie2, "2", 2)
        val fakeScreen3 = Screen(2, FakeMovieRepository.fakeMovie3, "3", 3)
    }
}

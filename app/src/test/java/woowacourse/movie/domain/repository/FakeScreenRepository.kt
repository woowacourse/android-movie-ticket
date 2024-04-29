package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.DateRange
import woowacourse.movie.domain.model.Grade
import woowacourse.movie.domain.model.Position
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats
import java.time.LocalDate

class FakeScreenRepository : ScreenRepository {
    private val screens = listOf(fakeScreen1, fakeScreen2, fakeScreen3)

    override fun load(): List<Screen> {
        return screens
    }

    override fun findById(id: Int): Result<Screen> = runCatching { screens.find { it.id == id } ?: throw NoSuchElementException() }

    override fun seats(screenId: Int): Seats =
        Seats(
            Seat(Position(0, 0), Grade.S),
            Seat(Position(1, 1), Grade.A),
            Seat(Position(2, 2), Grade.B),
        )

    companion object {
        val fakeScreen1 =
            Screen(1, FakeMovieRepository.fakeMovie1, DateRange(LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 3)))
        val fakeScreen2 =
            Screen(2, FakeMovieRepository.fakeMovie2, DateRange(LocalDate.of(2024, 3, 2), LocalDate.of(2024, 3, 4)))
        val fakeScreen3 =
            Screen(2, FakeMovieRepository.fakeMovie3, DateRange(LocalDate.of(2024, 3, 3), LocalDate.of(2024, 3, 5)))
    }
}

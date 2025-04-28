package woowacourse.movie.domain.model.movie

import java.time.LocalDate

class ScreeningMovies {
    fun getData(): List<Movie> = listOf(
        Movie(
            "승부",
            LocalDate.of(2025, 3, 26),
            LocalDate.of(2025, 5, 26),
            115,
        ),
        Movie(
            "미키 17",
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2025, 5, 13),
            137,
        ),
        Movie(
            "야당",
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2025, 6, 1),
            123,
        ),
        Movie(
            "위플래쉬",
            LocalDate.of(2025, 3, 12),
            LocalDate.of(2025, 5, 31),
            106,
        ),
        Movie(
            "고독한 미식가 더 무비",
            LocalDate.of(2025, 3, 19),
            LocalDate.of(2025, 5, 8),
            110,
        ),
        Movie(
            "너의 췌장을 먹고 싶어",
            LocalDate.of(2025, 4, 9),
            LocalDate.of(2025, 6, 1),
            115,
        ),
        Movie(
            "로비",
            LocalDate.of(2025, 4, 2),
            LocalDate.of(2025, 5, 30),
            106,
        )
    )
}
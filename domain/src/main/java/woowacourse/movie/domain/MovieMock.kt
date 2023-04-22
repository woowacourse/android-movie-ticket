package woowacourse.movie.domain

import java.time.LocalDate

object MovieMock {
    fun createMovie(): Movie = createMovies()[0]

    fun createMovies(): List<Movie> = listOf(
        Movie(
            Image(0),
            "해리 포터 1",
            DateRange(
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 31),
            ),
            111,
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
        ),
        Movie(
            Image(1),
            "해리 포터 2",
            DateRange(
                LocalDate.of(2022, 2, 1),
                LocalDate.of(2022, 2, 28),
            ),
            222,
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
        ),
        Movie(
            Image(2),
            "해리 포터 3",
            DateRange(
                LocalDate.of(2023, 3, 1),
                LocalDate.of(2023, 3, 31),
            ),
            333,
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
        ),
        Movie(
            Image(3),
            "해리 포터 4",
            DateRange(
                LocalDate.of(2024, 4, 1),
                LocalDate.of(2024, 4, 30),
            ),
            444,
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
        ),
    )
}

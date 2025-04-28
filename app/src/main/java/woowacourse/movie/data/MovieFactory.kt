package woowacourse.movie.data

import woowacourse.movie.R
import woowacourse.movie.domain.Date
import woowacourse.movie.domain.Movie
import java.time.LocalDate

class MovieFactory {
    fun getAll(): List<Movie> {
        return createMovies(100)
    }

    private fun createMovies(number: Int): List<Movie> {
        val movies: MutableList<Movie> = mutableListOf()
        for (index in 1..number) {
            val harryPotterAndThePhilosopherStone =
                Movie(
                    R.drawable.harry_potter_and_the_philosopher_stone,
                    "해리 포터와 마법사의 돌 $index",
                    Date(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 25)),
                    152,
                )
            movies.add(harryPotterAndThePhilosopherStone)
            val harryPotterAndTheChamberOfSecrets =
                Movie(
                    R.drawable.harry_potter_and_the_chamber_of_secrets,
                    "해리 포터와 비밀의 방 $index",
                    Date(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 28)),
                    152,
                )
            movies.add(harryPotterAndTheChamberOfSecrets)
            val harryPotterAndThePrisonerOfAzkaban =
                Movie(
                    R.drawable.harry_potter_and_the_prisoner_of_azkaban,
                    "해리 포터와 아즈카반의 죄수 $index",
                    Date(LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 31)),
                    152,
                )
            movies.add(harryPotterAndThePrisonerOfAzkaban)
            val harryPotterAndTheGobletOfFire =
                Movie(
                    R.drawable.harry_potter_and_the_goblet_of_fire,
                    "해리 포터와 불의 잔 $index",
                    Date(LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 30)),
                    152,
                )
            movies.add(harryPotterAndTheGobletOfFire)
        }
        return movies.toList()
    }
}

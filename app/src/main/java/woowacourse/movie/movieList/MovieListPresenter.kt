package woowacourse.movie.movieList

import MovieListView
import woowacourse.movie.model.MovieDisplayData
import woowacourse.movie.model.movieInfo.MovieDate
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.theater.Seat
import woowacourse.movie.model.theater.Theater
import java.time.LocalDate

class MovieListPresenter(private val view: MovieListView) {
    private fun generateSeats(): Map<String, Seat> {
        val seats = mutableMapOf<String, Seat>()
        val rows = 5
        val columns = 4

        for (row in 0 until rows) {
            for (col in 1..columns) {
                val seatId = "${('A' + row)}$col"
                val grade = when (row) {
                    0, 1 -> "B"
                    2, 3 -> "S"
                    else -> "A"
                }
                seats[seatId] = Seat(('A' + row), col, grade)
            }
        }
        return seats
    }


    private fun generateTheaters(count: Int): List<Theater> {
        return makeDummyData(count)
    }

    private fun makeDummyData(count: Int): List<Theater> {
        return (1..count).map { i ->
            val movie = MovieInfo(
                Title("차람과 하디의 진지한 여행기 $i"),
                MovieDate(LocalDate.of(2024, 2, 25).plusDays(i.toLong())),
                RunningTime(100 + i % 150),
                Synopsis("Synopsis for movie $i")
            )
            val seats = generateSeats()
            Theater(movie, seats)
        }
    }

    private val theaters: List<Theater> = generateTheaters(GENERATE_DUMMY_DATA_NUM)

    private fun convertToDisplayData(theaters: List<Theater>): List<MovieDisplayData> {
        return theaters.map { theater ->
            MovieDisplayData(
                title = theater.movie.title.toString(),
                releaseDate = theater.movie.releaseDate.toString(),
                runningTime = theater.movie.runningTime.toString()
            )
        }
    }

    fun onDetailButtonClicked(position: Int) {
        val theater = theaters[position]
        view.navigateToMovieDetail(theater)
    }

    fun loadMovies() {
        val displayData = convertToDisplayData(theaters)
        view.updateAdapter(displayData)
    }

    companion object {
        const val GENERATE_DUMMY_DATA_NUM = 10000
    }
}

package woowacourse.movie.movie

import android.content.Intent
import woowacourse.movie.BookingDetailActivity
import woowacourse.movie.mapper.IntentCompat
import woowacourse.movie.model.Movie
import java.time.LocalDate

class MoviePresenter(
    private val view: MovieContract.View,
) : MovieContract.Presenter {
    override fun initializeData(intent: Intent) {
        val movie =
            IntentCompat.getParcelableExtra(
                intent,
                BookingDetailActivity.Companion.KEY_MOVIE_DATA,
                Movie::class.java,
            )

        if (movie == null) {
            view.showToast("기본 영화 목록을 불러왔습니다.")
        }

        val movies = movie?.let { listOf(it) } ?: mockMovieList()
        view.showMovies(movies)
    }

    override fun onReserveClicked(movie: Movie) {
        view.startBookingActivity(movie)
    }

    private fun mockMovieList(): List<Movie> {
        return listOf(
            Movie(
                title = "해리 포터와 마법사의 돌",
                imageSource = "harry_potter.png",
                screeningStartDate = LocalDate.of(2025, 4, 1),
                screeningEndDate = LocalDate.of(2025, 4, 25),
                runningTime = 152,
            ),
            Movie(
                title = "스타 이즈 본",
                imageSource = "star_is_born.jpg",
                screeningStartDate = LocalDate.of(2025, 4, 19),
                screeningEndDate = LocalDate.of(2025, 5, 25),
                runningTime = 135,
            ),
        )
    }
}
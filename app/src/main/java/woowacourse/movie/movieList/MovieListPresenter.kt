package woowacourse.movie.movieList

import android.content.Intent
import woowacourse.movie.model.movieInfo.MovieDate
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.theater.Theater
import woowacourse.movie.movieDetail.MovieDetailActivity
import java.time.LocalDate

class MovieListPresenter(private val view: MovieListActivity) {
    private val movieInfo = MovieInfo(
        Title("차람과 하디의 진지한 여행기"),
        MovieDate(LocalDate.of(2024, 2, 25)),
        RunningTime(230),
        Synopsis("wow!")
    )

    private val theater = Theater(movieInfo)
    val theaters: List<Theater> = listOf(theater)

    fun onDetailButtonClicked(position: Int) {
        val theater = theaters[position]
        val intent = Intent(view, MovieDetailActivity::class.java).apply {
            putExtra("Theater", theater)
        }
        view.startActivity(intent)
    }
}

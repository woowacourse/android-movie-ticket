package woowacourse.movie.mainpage

import android.content.Context
import android.content.Intent
import woowacourse.movie.movieDetail.MovieDetailActivity
import woowacourse.movie.model.movieInfo.MovieDate
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.theater.Theater
import java.time.LocalDate

class MainActivityPresenter(private val context: Context) {

    private val movieInfo = MovieInfo(
        Title("차람과 하디의 진지한 여행기"),
        MovieDate(LocalDate.of(2024, 2, 25)),
        RunningTime(230),
        Synopsis("wow!")
    )

    private val theater = Theater(movieInfo)
    private val theaterList = listOf(theater)
    val movieAdapter = MovieAdapter(context, theaterList, this)

    fun onDetailButtonClicked(theater: Theater) {
        val intent = Intent(context, MovieDetailActivity::class.java).apply {
            putExtra("Theater", theater)
        }
        context.startActivity(intent)
    }
}

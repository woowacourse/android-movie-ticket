package woowacourse.movie.presenter

import android.content.Context
import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.model.movieInfo.MovieDate
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.theater.Theater
import woowacourse.movie.repository.PseudoTheaterRepository
import woowacourse.movie.repository.TheaterRepository
import java.time.LocalDate

class MovieListActivityPresenter(
    private val context: Context,
    private val theaterRepository: TheaterRepository = PseudoTheaterRepository()
) {
    val movieAdapter = MovieAdapter(context, theaterRepository.getTheaters())
}
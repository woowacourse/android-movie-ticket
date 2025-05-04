package woowacourse.movie.ui.main

import woowacourse.movie.R
import woowacourse.movie.domain.model.advertisement.Advertisement
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.domain.model.movie.RunningMinute
import woowacourse.movie.domain.model.movie.ScreeningDate
import woowacourse.movie.domain.policy.MovieAdvertisementPolicy
import woowacourse.movie.ui.model.toAdvertisementUiModel
import woowacourse.movie.ui.model.toMovieUiModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainPresenter(
    private val view: MainContract.View,
    private var movies: List<Movie> = emptyList(),
    private var advertisements: List<Advertisement> = emptyList(),
) : MainContract.Presenter {
    init {
        if (movies.isEmpty()) initMovies()
        if (advertisements.isEmpty()) initAdvertisementResources()
    }

    private fun initMovies() {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
        val startDate: LocalDate = LocalDate.parse("2025.04.15", formatter)
        val endDate: LocalDate = LocalDate.parse("2025.05.23", formatter)

        movies =
            List(10) {
                Movie(
                    "해리포터와 마법사의 돌",
                    ScreeningDate(startDate, endDate),
                    RunningMinute(152),
                    R.drawable.harrypotter.toString(),
                )
            }
    }

    private fun initAdvertisementResources() {
        advertisements =
            listOf(
                Advertisement(R.drawable.baemin.toString()),
                Advertisement(R.drawable.baemina.toString()),
            )
    }

    override fun showMovies() {
        val movieAdvertisementPolicy = MovieAdvertisementPolicy()
        val movieAdvertisements =
            movieAdvertisementPolicy.movieAdvertisement(
                movies,
                advertisements,
                ADVERTISEMENT_INTERVAL,
            )
        val movieItems =
            movieAdvertisements.map { movieAdvertisement ->
                when (movieAdvertisement) {
                    is Movie -> movieAdvertisement.toMovieUiModel()
                    is Advertisement -> movieAdvertisement.toAdvertisementUiModel()
                    else -> throw IllegalStateException("처리하지 않은 상태가 있습니다.")
                }
            }
        view.showMovies(movieItems)
    }

    private companion object {
        private const val ADVERTISEMENT_INTERVAL = 3
        const val DATE_FORMAT = "yyyy.MM.dd"
    }
}

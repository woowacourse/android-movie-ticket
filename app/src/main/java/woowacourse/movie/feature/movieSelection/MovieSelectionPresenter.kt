package woowacourse.movie.feature.movieSelection

import woowacourse.movie.R
import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.feature.model.movie.MovieListItem.Ad
import woowacourse.movie.feature.model.movie.MovieListItem.MovieUiModel
import woowacourse.movie.feature.model.toUiModel
import java.time.LocalDate

class MovieSelectionPresenter(
    private val view: MovieSelectionContract.View,
) : MovieSelectionContract.Presenter {
    override fun initializeMovies() {
        var counter = 1
        view.showMovies(
            (1..10000).map { n ->
                if (n % 4 != 0) {
                    Movie(
                        "해리 포터 ${counter++}",
                        startDate = LocalDate.of(2025, 4, 1),
                        endDate = LocalDate.of(2025, 5, 31),
                        runningTime = 152,
                    ).toUiModel()
                } else {
                    Ad(R.drawable.ad)
                }
            },
        )
    }

    override fun selectMovie(movie: MovieUiModel) {
        view.goToReservation(movie)
    }
}

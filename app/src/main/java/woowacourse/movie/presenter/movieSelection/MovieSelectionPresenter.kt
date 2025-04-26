package woowacourse.movie.presenter.movieSelection

import woowacourse.movie.domain.Movie
import woowacourse.movie.view.model.MovieUiModel
import woowacourse.movie.view.model.toUiModel
import java.time.LocalDate

class MovieSelectionPresenter(
    private val view: MovieSelectionContract.View,
) : MovieSelectionContract.Presenter {
    override fun loadMovies() {
        view.showMovies(
            (1..10000).map { n ->
                Movie(
                    "해리 포터 $n",
                    startDate = LocalDate.of(2025, 4, 1),
                    endDate = LocalDate.of(2025, 5, 31),
                    runningTime = 152,
                ).toUiModel()
            },
        )
    }

    override fun onMovieSelection(movie: MovieUiModel) {
        view.selectMovie(movie)
    }
}

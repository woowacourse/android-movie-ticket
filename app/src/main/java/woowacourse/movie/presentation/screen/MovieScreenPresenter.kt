package woowacourse.movie.presentation.screen

import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.presentation.screen.model.MovieModel

class MovieScreenPresenter(
    private val view: MovieScreenContract.View,
    private val movieRepository: MovieRepository,
) : MovieScreenContract.Presenter {
    override fun loadScreenMovies() {
        val movies = movieRepository.loadMovies()
        view.showScreenMovies(movies)
    }

    override fun clickReservationButton(
        title: String,
        posterResourceId: Int,
        screeningDate: String,
        runningTime: Int,
        description: String
    ) {
        val movieModel = MovieModel(
            title = title,
            posterResourceId = posterResourceId,
            screeningDate = screeningDate,
            runningTime = runningTime,
            description = description
        )
        view.moveToReservation(movieModel)
    }
}

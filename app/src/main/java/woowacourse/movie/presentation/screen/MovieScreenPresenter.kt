package woowacourse.movie.presentation.screen

import woowacourse.movie.presentation.screen.model.MovieModel

class MovieScreenPresenter(
    private val view: MovieScreenContract.View,
) : MovieScreenContract.Presenter {
    override fun loadScreenMovies() {
//        view.showScreenMovies()
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

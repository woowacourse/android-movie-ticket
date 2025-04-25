package woowacourse.movie.view.movies

import woowacourse.movie.domain.model.ad.Advertisement
import woowacourse.movie.domain.model.movies.Movie
import woowacourse.movie.view.StringFormatter
import woowacourse.movie.view.movies.model.UiModel

class MovieListPresenter(
    private val view: MovieListContract.View,
    private val model: List<Movie>,
) : MovieListContract.Presenter {
    override fun loadUiData() {
        val items = mutableListOf<UiModel>()

        model.forEachIndexed { index, movie ->
            items.add(movie.toUiModel())
            if ((index + 1) % AD_DIVIDE_STANDARD == 0) {
                items.add(Advertisement().toUiModel())
            }
        }
        view.showMovieList(items)
    }

    override fun onSelectMovie(movieIdx: Int) = view.moveToBookingComplete(movieIdx)

    private fun Movie.toUiModel(): UiModel.MovieUiModel {
        return UiModel.MovieUiModel(
            id = id,
            title = title,
            imgName = posterResource,
            releaseStartDate = StringFormatter.dotDateFormat(releaseDate.startDate),
            releaseEndDate = StringFormatter.dotDateFormat(releaseDate.endDate),
            runningTime = runningTime,
        )
    }

    private fun Advertisement.toUiModel(): UiModel.AdvertiseUiModel {
        return UiModel.AdvertiseUiModel(
            imgResource = imgResource,
        )
    }

    companion object {
        private const val AD_DIVIDE_STANDARD = 3
    }
}

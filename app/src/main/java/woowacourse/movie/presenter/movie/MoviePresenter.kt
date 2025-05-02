package woowacourse.movie.presenter.movie

import android.util.Log
import woowacourse.movie.domain.model.ads.Ads
import woowacourse.movie.sample.SampleMovies
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.util.mapper.MovieModelMapper

class MoviePresenter(
    private val view: MovieContract.View,
) : MovieContract.Presenter {
    private val sampleMovies = SampleMovies()
    private val ads = Ads()

    override fun loadMovies() {
        runCatching {
            validateMovie(sampleMovies.movieUiModels)
        }.onSuccess {
            view.showMovies(sampleMovies.movieUiModels, ads)
        }.onFailure { exception ->
            // 내부에서는 exception의 메시지를 활용해서 원인 분석
            Log.d("MoviePresenter", exception.message ?: "예외 발생 원인을 찾아야 합니다.")
            view.showErrorMessage(ERROR_MOVIE_LOAD_FAIL)
        }
    }

    override fun onMovieSelect(movieUiModel: MovieUiModel) {
        view.moveTo(movieUiModel)
    }

    private fun validateMovie(movieUiModels: List<MovieUiModel>) {
        movieUiModels.map { uiModel -> MovieModelMapper.toDomain(uiModel) }
    }

    companion object {
        private const val ERROR_MOVIE_LOAD_FAIL = "영화 정보를 읽어오는 데 실패했습니다"
    }
}

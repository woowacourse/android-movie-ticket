package woowacourse.movie.presentation.presenter

import woowacourse.movie.data.repository.AdRepositoryImpl
import woowacourse.movie.data.repository.MovieRepositoryImpl
import woowacourse.movie.domain.admodel.Ad
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.repository.AdRepository
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.presentation.contract.MainContract
import woowacourse.movie.presentation.uimodel.MovieUiModel

class MainPresenterImpl(
    private val movieRepository: MovieRepository = MovieRepositoryImpl,
    private val adRepository: AdRepository = AdRepositoryImpl,
) :
    MainContract.Presenter {
    private var view: MainContract.View? = null
    private lateinit var movies: List<Movie>
    private lateinit var ads: List<Ad>

    override fun attachView(view: MainContract.View) {
        this.view = view
        onViewSetUp()
    }

    override fun detachView() {
        this.view = null
    }

    override fun onViewSetUp() {
        loadMovie()
        loadAds()
    }

    override fun loadMovie() {
        movies = movieRepository.createMovieList()
        view?.onUpdateMovies(
            movies.map { MovieUiModel(it) },
        )
    }

    override fun loadAds() {
        ads = adRepository.getAds()
        view?.onUpdateAds(ads)
    }

    override fun onReserveButtonClicked(movieId: Int) {
        view?.moveToMovieDetail(movieId)
    }
}

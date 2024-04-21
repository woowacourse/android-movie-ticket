package woowacourse.movie.feature.home

class MovieHomePresenter(
    private val view: MovieHomeContract.View,
) : MovieHomeContract.Presenter {
    override fun setUpMovieContents() {
        view.setUpMovieContentListAdapter()
    }
}

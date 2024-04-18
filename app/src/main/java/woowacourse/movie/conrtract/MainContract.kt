package woowacourse.movie.conrtract

interface MainContract {
    interface View {
        fun setUpMovieContentListAdapter()
    }

    interface Presenter {
        fun saveMovieContent()
    }
}

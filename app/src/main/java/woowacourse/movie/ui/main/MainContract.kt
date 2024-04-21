package woowacourse.movie.ui.main

interface MainContract {
    interface View {
        fun setUpMovieContentListAdapter()
    }

    interface Presenter {
        fun saveMovieContent()
    }
}

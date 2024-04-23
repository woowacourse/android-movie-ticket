package woowacourse.movie.list

interface MovieListContract {
    interface View {
        val presenter: MovieListPresenter

        fun showMoviesInfo(info: ArrayList<Movie>)

        fun setOnListViewClickListener(info: ArrayList<Movie>)
    }

    interface Presenter {
        val movieList: ArrayList<Movie>

        fun setMoviesInfo()

        fun setListViewClickListenerInfo()
    }
}

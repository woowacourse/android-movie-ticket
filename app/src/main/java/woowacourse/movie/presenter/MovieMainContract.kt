package woowacourse.movie.presenter

interface MovieMainContract {
    interface View {
        fun onMovieItemClick(id: Long)
    }

    interface Presenter
}

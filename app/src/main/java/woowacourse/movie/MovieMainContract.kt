package woowacourse.movie

interface MovieMainContract {
    interface View {
        fun onMovieItemClick(movie: Movie)
    }

    interface Presenter
}

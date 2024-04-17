package woowacourse.movie

interface ViewInterface {
    fun onMovieItemClick(movie: Movie)
}

interface MovieChoiceContract {
    interface View {
        fun onMovieItemClick(movie: Movie)
    }

    interface Presenter
}

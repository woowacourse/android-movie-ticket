package woowacourse.movie.movies

interface MoviesContract {
    interface View {
        fun showMovies(uiModels: List<MovieUiModel>) {}

        fun navigateToBookingDetail(movieUiModel: MovieUiModel) {}
    }

    interface Presenter {
        fun loadMovies() {}

        fun onMovieSelected(movieUiModel: MovieUiModel) {}
    }
}

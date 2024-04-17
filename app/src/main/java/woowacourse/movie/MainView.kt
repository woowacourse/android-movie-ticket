package woowacourse.movie

interface MainView {
    fun displayMovies(movies: MutableList<Movie>)

    fun navigateToReservation(movie: Movie)
}

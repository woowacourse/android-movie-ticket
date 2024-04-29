package woowacourse.movie.seatselection.presenter

import woowacourse.movie.R
import woowacourse.movie.data.MovieRepository.getMovieById
import woowacourse.movie.model.MovieSelectedSeats
import woowacourse.movie.seatselection.presenter.contract.MovieSeatSelectionContract

class MovieSeatSelectionPresenter(
    private val movieSeatSelectionContractView: MovieSeatSelectionContract.View,
) : MovieSeatSelectionContract.Presenter {
    lateinit var movieSelectedSeats: MovieSelectedSeats

    override fun loadMovieTitle(id: Long) {
        val movieData = getMovieById(id)
        movieData?.let { movie ->
            movieSeatSelectionContractView.displayMovieTitle(movie.title)
        }
    }

    override fun loadTableSeats(count: Int) {
        movieSelectedSeats = MovieSelectedSeats(count)
        movieSeatSelectionContractView.setUpTableSeats(movieSelectedSeats.baseSeats)
    }

    override fun updateSelectedSeats(count: Int) {
        movieSelectedSeats = MovieSelectedSeats(count)
    }

    override fun clickTableSeat(index: Int) {
        val seat = movieSelectedSeats.baseSeats[index]
        if (movieSelectedSeats.isSelected(index)) {
            movieSeatSelectionContractView.updateSeatBackgroundColor(index, R.color.white)
            movieSelectedSeats.unSelectSeat(seat)
        } else {
            if (!movieSelectedSeats.isSelectionComplete()) {
                movieSeatSelectionContractView.updateSeatBackgroundColor(index, R.color.selected)
                movieSelectedSeats.selectSeat(seat)
            }
        }
        movieSeatSelectionContractView.updateSelectResult(movieSelectedSeats)
    }

    override fun clickPositiveButton() {
        movieSeatSelectionContractView.navigateToResultView(movieSelectedSeats)
    }
}

package woowacourse.movie.seatselection.presenter

import woowacourse.movie.R
import woowacourse.movie.data.MovieRepository
import woowacourse.movie.model.MovieSelectedSeats
import woowacourse.movie.seatselection.presenter.contract.MovieSeatSelectionContract

class MovieSeatSelectionPresenter(
    private val movieSeatSelectionContractView: MovieSeatSelectionContract.View,
    count: Int,
) : MovieSeatSelectionContract.Presenter {
    private val movieRepository: MovieRepository = MovieRepository()

    private val movieSelectedSeats: MovieSelectedSeats = MovieSelectedSeats(count)

    override fun loadSeatSelection(id: Long) {
        val movieData = movieRepository.getMovieById(id)
        movieData?.let { movie ->
            movieSeatSelectionContractView.displayMovieTitle(movieData.title)
        }
    }

    override fun loadTableSeats() {
        movieSeatSelectionContractView.setUpTableSeats(movieSelectedSeats.baseSeats)
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

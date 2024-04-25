package woowacourse.movie.seatselection.presenter

import woowacourse.movie.seatselection.presenter.contract.MovieSeatContract

class MovieSeatPresenter(private val movieSeatContractView: MovieSeatContract.View) : MovieSeatContract.Presenter

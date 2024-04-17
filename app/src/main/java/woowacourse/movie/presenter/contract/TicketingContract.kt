package woowacourse.movie.presenter.contract

import woowacourse.movie.model.Movie

interface TicketingContract {
    fun assignInitialView(
        movie: Movie,
        count: Int,
    )

    fun updateCount(count: Int)

    fun navigate(
        movie: Movie,
        count: Int,
    )
}

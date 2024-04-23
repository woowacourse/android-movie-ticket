package woowacourse.movie.presenter

import woowacourse.movie.model.MovieTicket

interface MovieResultContract {
    interface View : BaseContract.View {
        fun onInitView(movieTicket: MovieTicket)
    }

    interface Presenter {
        fun display(
            id: Long,
            count: Int,
        )
    }
}

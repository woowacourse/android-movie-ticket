package woowacourse.movie.result.presenter

import woowacourse.movie.model.MovieTicket
import woowacourse.movie.presenter.BaseContract

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

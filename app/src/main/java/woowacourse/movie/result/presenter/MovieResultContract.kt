package woowacourse.movie.result.presenter

import woowacourse.movie.base.BaseContract
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

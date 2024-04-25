package woowacourse.movie.presentation.result

import woowacourse.movie.domain.MovieTicket
import woowacourse.movie.presentation.base.BaseContract

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

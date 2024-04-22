package woowacourse.movie.presenter

import woowacourse.movie.model.MovieTicket
import woowacourse.movie.utils.MovieErrorCode

interface MovieResultContract {
    interface View {
        fun onInitView(movieTicket: MovieTicket)

        fun onError(errorCode: MovieErrorCode)
    }

    interface Presenter {
        fun display(
            id: Long,
            count: Int,
        )
    }
}

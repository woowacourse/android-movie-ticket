package woowacourse.movie.presentation.result

import woowacourse.movie.presentation.base.BaseContract

interface MovieResultContract {
    interface View : BaseContract.View {
        fun onInitView(resultUiModel: ResultUiModel)
    }

    interface Presenter {
        fun display(
            movieId: Long,
            movieScreenDateTimeId: Long,
            seatIds: List<Long>,
            count: Int,
        )
    }
}

package woowacourse.movie.presentation.result

import woowacourse.movie.presentation.base.BaseContract

interface MovieResultContract {
    interface View : BaseContract.View {
        fun onUpdateView(resultUiModel: ResultUiModel)
    }

    interface Presenter {
        fun loadResult(
            movieId: Long,
            movieScreenDateTimeId: Long,
            seatIds: List<Long>,
            count: Int,
        )
    }
}

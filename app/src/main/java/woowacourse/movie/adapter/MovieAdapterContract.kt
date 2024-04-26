package woowacourse.movie.adapter

import woowacourse.movie.model.movie.Movie

interface MovieAdapterContract {
    interface View {
        fun notifyItemClicked(position: Int)
    }

    interface Model {
    }
}

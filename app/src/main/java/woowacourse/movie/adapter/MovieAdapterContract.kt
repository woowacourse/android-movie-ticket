package woowacourse.movie.adapter

interface MovieAdapterContract {
    interface View {
        fun notifyItemClicked(position: Int)
    }

    interface Model
}

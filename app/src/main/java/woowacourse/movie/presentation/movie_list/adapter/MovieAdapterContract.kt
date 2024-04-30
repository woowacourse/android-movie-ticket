package woowacourse.movie.presentation.movie_list.adapter

interface MovieAdapterContract {
    interface View {
        fun notifyItemClicked(position: Int)
    }

    interface Model
}

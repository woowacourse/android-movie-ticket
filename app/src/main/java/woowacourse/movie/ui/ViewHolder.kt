package woowacourse.movie.ui

import android.view.View

interface ViewHolder<T> {
    fun view(): View

    fun bind(item: T)
}

package woowacourse.movie.ui

import android.view.View
import android.view.ViewGroup

interface ViewHolderContainer<T> {
    fun viewHolder(
        convertView: View?,
        parent: ViewGroup,
    ): ViewHolder<T>
}

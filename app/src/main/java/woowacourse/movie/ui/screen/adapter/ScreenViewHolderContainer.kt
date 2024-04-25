package woowacourse.movie.ui.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import woowacourse.movie.R
import woowacourse.movie.ui.ScreenPreviewUI
import woowacourse.movie.ui.ViewHolder
import woowacourse.movie.ui.ViewHolderContainer
import woowacourse.movie.ui.screen.OnItemClickListener

class ScreenViewHolderContainer(private val onScreenClickListener: OnItemClickListener<Int>) :
    ViewHolderContainer<ScreenPreviewUI> {
    private val viewHolders = mutableMapOf<Int, ViewHolder<ScreenPreviewUI>>()

    override fun viewHolder(
        convertView: View?,
        parent: ViewGroup,
    ): ViewHolder<ScreenPreviewUI> = viewHolders[convertView?.hashCode()] ?: createViewHolder(parent, onScreenClickListener)

    private fun createViewHolder(
        parent: ViewGroup,
        onScreenClickListener: OnItemClickListener<Int>,
    ): ScreenViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_screen, parent, false)
        val viewHolder = ScreenViewHolder(view, onScreenClickListener)
        viewHolders[view.hashCode()] = viewHolder
        return viewHolder
    }
}

package woowacourse.movie.ui.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import woowacourse.movie.R
import woowacourse.movie.ui.screen.OnScreenClickListener

class ScreenViewHolderContainer {
    private val viewHolders = mutableMapOf<Int, ScreenViewHolder>()

    fun viewHolder(
        convertView: View?,
        parent: ViewGroup,
        onScreenClickListener: OnScreenClickListener,
    ): ScreenViewHolder {
        return viewHolders[convertView?.hashCode()] ?: createViewHolder(parent, onScreenClickListener)
    }

    private fun createViewHolder(
        parent: ViewGroup,
        onScreenClickListener: OnScreenClickListener,
    ): ScreenViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_screen, parent, false)
        val viewHolder = ScreenViewHolder(view, onScreenClickListener)
        viewHolders[view.hashCode()] = viewHolder
        return viewHolder
    }
}

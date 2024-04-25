package woowacourse.movie.ui.screen.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.ui.ScreenPreviewUI
import woowacourse.movie.ui.screen.OnScreenClickListener

class ScreenAdapter(
    private var item: List<ScreenPreviewUI>,
    private val onScreenClickListener: OnScreenClickListener,
) : BaseAdapter() {
    private val viewHolderContainer = ScreenViewHolderContainer()

    override fun getCount(): Int = item.size

    override fun getItem(position: Int): ScreenPreviewUI = item[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val viewHolder = viewHolderContainer.viewHolder(convertView, parent, onScreenClickListener)
        viewHolder.bind(item[position])
        return viewHolder.view
    }

    fun updateScreens(screens: List<ScreenPreviewUI>) {
        item = screens
        notifyDataSetChanged()
    }
}

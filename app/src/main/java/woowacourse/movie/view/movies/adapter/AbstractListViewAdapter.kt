package woowacourse.movie.view.movies.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

abstract class AbstractListViewAdapter<T : Any, VH : AbstractListViewAdapter.ViewHolder> :
    BaseAdapter() {
    abstract class ViewHolder(val itemView: View)

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val viewHolder: VH
        val itemView: View

        if (convertView == null) {
            viewHolder = onCreateViewHolder(parent, getItemViewType(position))
            itemView = viewHolder.itemView
            itemView.tag = viewHolder
        } else {
            itemView = convertView
            viewHolder = itemView.tag as VH
        }

        onBindViewHolder(viewHolder, position)

        return itemView
    }

    abstract fun onBindViewHolder(
        viewHolder: VH,
        position: Int,
    )

    abstract fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): VH
}

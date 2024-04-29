package woowacourse.movie.ui.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.movie.R
import woowacourse.movie.ui.ScreenPreviewUI

class ScreenAdapter(
    private val onItemClick: (id: Int) -> Unit,
) : ListAdapter<ScreenPreviewUI, ScreenViewHolder>(
        object : DiffUtil.ItemCallback<ScreenPreviewUI>() {
            override fun areItemsTheSame(
                oldItem: ScreenPreviewUI,
                newItem: ScreenPreviewUI,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ScreenPreviewUI,
                newItem: ScreenPreviewUI,
            ): Boolean {
                return oldItem == newItem
            }
        },
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ScreenViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.holder_screen, parent, false)
        return ScreenViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(
        holder: ScreenViewHolder,
        position: Int,
    ) {
        val screen = getItem(position)
        holder.bind(screen)
    }
}

package woowacourse.movie.feature.movies.view.adapter

import androidx.recyclerview.widget.DiffUtil

object DiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(
        oldItem: Item,
        newItem: Item,
    ): Boolean = oldItem.id == newItem.id && oldItem::class == newItem::class

    override fun areContentsTheSame(
        oldItem: Item,
        newItem: Item,
    ): Boolean = oldItem == newItem
}

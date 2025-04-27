package woowacourse.movie.view.movie.adapter

import androidx.recyclerview.widget.DiffUtil
import woowacourse.movie.model.Movie

object MoviesDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(
        oldItem: Movie,
        newItem: Movie,
    ): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: Movie,
        newItem: Movie,
    ): Boolean = oldItem == newItem
}

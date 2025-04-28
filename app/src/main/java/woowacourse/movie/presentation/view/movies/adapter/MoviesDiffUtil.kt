package woowacourse.movie.presentation.view.movies.adapter

import androidx.recyclerview.widget.DiffUtil
import woowacourse.movie.presentation.model.MovieUiModel

object MoviesDiffUtil : DiffUtil.ItemCallback<MovieUiModel>() {
    override fun areItemsTheSame(
        oldItem: MovieUiModel,
        newItem: MovieUiModel,
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: MovieUiModel,
        newItem: MovieUiModel,
    ): Boolean = oldItem == newItem
}

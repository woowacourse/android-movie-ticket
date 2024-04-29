package woowacourse.movie.presentation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import woowacourse.movie.presentation.uimodel.MovieUiModel

class MovieDiffCallback(
    private val oldList: List<MovieUiModel>,
    private val newList: List<MovieUiModel>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean {
        return oldList[oldItemPosition].movieId == newList[newItemPosition].movieId
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

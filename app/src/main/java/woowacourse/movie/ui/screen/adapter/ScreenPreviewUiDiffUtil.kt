package woowacourse.movie.ui.screen.adapter

import androidx.recyclerview.widget.DiffUtil

class ScreenPreviewUiDiffUtil : DiffUtil.ItemCallback<ScreenAd>() {
    override fun areItemsTheSame(oldItem: ScreenAd, newItem: ScreenAd): Boolean {
        return oldItem.equalsWith(newItem)
    }

    override fun areContentsTheSame(oldItem: ScreenAd, newItem: ScreenAd): Boolean {
        return oldItem.equalsWith(newItem)
    }
}
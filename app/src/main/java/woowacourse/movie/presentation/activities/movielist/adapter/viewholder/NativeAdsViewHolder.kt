package woowacourse.movie.presentation.activities.movielist.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.databinding.ItemNativeAdsBinding
import woowacourse.movie.presentation.model.movieitem.Ad

class NativeAdsViewHolder(
    private val binding: ItemNativeAdsBinding,
    private val onAdsClick: (Ad) -> Unit = {},
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Ad) {
        binding.ivNativeAds.setImageResource(item.bannerResId)
        binding.root.setOnClickListener { onAdsClick(item) }
    }
}

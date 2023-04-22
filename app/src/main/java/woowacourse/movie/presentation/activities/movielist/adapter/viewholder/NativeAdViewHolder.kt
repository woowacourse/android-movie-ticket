package woowacourse.movie.presentation.activities.movielist.adapter.viewholder

import android.view.View
import android.widget.ImageView
import woowacourse.movie.R
import woowacourse.movie.presentation.base.BaseRecyclerView
import woowacourse.movie.presentation.model.movieitem.Ad
import woowacourse.movie.presentation.model.movieitem.ListItem

class NativeAdViewHolder(
    view: View,
    onAdClick: (Int) -> Unit,
) : BaseRecyclerView.BaseViewHolder(view) {
    private val nativeAdsImageView: ImageView = view.findViewById(R.id.native_ad_iv)

    init {
        nativeAdsImageView.setOnClickListener { onAdClick(adapterPosition) }
    }

    override fun <T : ListItem> bind(item: T) {
        if (item !is Ad) return
        nativeAdsImageView.setImageResource(item.bannerResId)
    }
}

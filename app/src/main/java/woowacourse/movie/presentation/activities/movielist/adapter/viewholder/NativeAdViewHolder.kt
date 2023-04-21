package woowacourse.movie.presentation.activities.movielist.adapter.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.model.movieitem.Ad

class NativeAdViewHolder(
    view: View,
    onAdClick: (Int) -> Unit = {},
) : RecyclerView.ViewHolder(view) {
    private val nativeAdsImageView: ImageView = view.findViewById(R.id.native_ad_iv)

    init {
        view.setOnClickListener { onAdClick(adapterPosition) }
    }

    fun bind(item: Ad) {
        nativeAdsImageView.setImageResource(item.bannerResId)
    }
}

package woowacourse.movie.view.movie.adapter

import android.view.View
import android.widget.ImageView
import woowacourse.movie.R
import woowacourse.movie.view.movie.model.AdUiModel

class AdsViewHolder(
    view: View,
    listener: ClickListener,
) : BaseViewHolder<MovieListItem.AdItem>(view) {
    private lateinit var currentItem: AdUiModel
    private val image: ImageView = view.findViewById(R.id.ad_image)

    init {
        view.setOnClickListener {
            listener.onClickAd(currentItem)
        }
    }

    override fun bind(item: MovieListItem.AdItem) {
        currentItem = item.ad
        image.setImageResource(item.ad.imageResId)
    }

    interface ClickListener {
        fun onClickAd(item: AdUiModel)
    }
}

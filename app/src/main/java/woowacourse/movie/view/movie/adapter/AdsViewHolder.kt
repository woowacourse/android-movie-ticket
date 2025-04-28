package woowacourse.movie.view.movie.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.movie.model.AdUiModel

class AdsViewHolder(
    view: View,
    listener: MovieItemClickListener,
) : RecyclerView.ViewHolder(view) {
    private var currentItem: AdUiModel? = null
    private val image: ImageView = view.findViewById(R.id.ad_image)

    init {
        view.setOnClickListener {
            currentItem?.let { listener.onAdClick(it) }
        }
    }

    fun bind(item: AdUiModel) {
        currentItem = item
        image.setImageResource(item.imageResId)
    }
}

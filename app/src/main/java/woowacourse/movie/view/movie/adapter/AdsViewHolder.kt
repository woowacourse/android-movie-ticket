package woowacourse.movie.view.movie.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.movie.model.AdUiModel

class AdsViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.ad_image)

    fun bind(item: AdUiModel) {
        image.setImageResource(item.imageResId)
    }
}

package woowacourse.movie.view.movies.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.ext.toDrawableResourceId
import woowacourse.movie.view.movies.model.UiModel.AdvertiseUiModel

class AdvertiseViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {
    private val context = itemView.context
    private val imgAdvertise = itemView.findViewById<ImageView>(R.id.img_ad)

    fun bind(item: AdvertiseUiModel) {
        imgAdvertise.setImageResource(item.imgResource.toDrawableResourceId(context))
    }
}

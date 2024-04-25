package woowacourse.movie.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class AdvertisementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val banner: ImageView = view.findViewById(R.id.image_view_item_advertisement)

    fun bind() {
        banner.setImageResource(R.drawable.advertisement_wooteco)
    }
}

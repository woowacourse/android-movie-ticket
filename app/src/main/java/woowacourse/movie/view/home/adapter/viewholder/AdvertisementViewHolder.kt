package woowacourse.movie.view.home.adapter.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.advertisement.Advertisement

class AdvertisementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val banner: ImageView = view.findViewById(R.id.image_view_item_advertisement)

    fun bind(ads: Advertisement) {
        banner.setImageResource(ads.banner)
    }
}

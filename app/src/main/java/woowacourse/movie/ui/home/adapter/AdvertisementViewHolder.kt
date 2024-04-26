package woowacourse.movie.ui.home.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class AdvertisementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val advertisementPoster: ImageView = view.findViewById(R.id.ad_image)

    fun bind() {
        advertisementPoster.setImageResource(R.drawable.advertisement_poster)
    }
}

package woowacourse.movie.main

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieAndAd

class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.ad_image)

    fun bind(ad: MovieAndAd.Advertisement) {
        image.setImageResource(ad.imgResourceId)
    }
}

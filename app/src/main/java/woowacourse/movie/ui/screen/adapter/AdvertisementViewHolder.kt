package woowacourse.movie.ui.screen.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.ScreenAd

class AdViewHolder(view: View, private val onItemClick: (id: Int) -> Unit) : RecyclerView.ViewHolder(view) {
    private val adImage: ImageView = view.findViewById(R.id.iv_advertisement)

    fun bind(ad: ScreenAd.Advertisement) {
        adImage.setImageResource(ad.advertisement.imageSource)
        adImage.setOnClickListener {
            onItemClick(ad.id)
        }
    }
}

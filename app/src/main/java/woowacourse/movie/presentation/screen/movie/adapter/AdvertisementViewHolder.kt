package woowacourse.movie.presentation.screen.movie.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R

class AdvertisementViewHolder(view: View): ViewHolder(view) {
    private val banner = view.findViewById<ImageView>(R.id.advertisement_imageview)

    init {
        banner.setImageResource(R.drawable.screening_advertisement)
    }
}

package woowacourse.movie.ui.view.movie

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class AdsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val adsImage: ImageView by lazy { view.findViewById(R.id.img_ads) }

    fun bind() {
        adsImage.setImageResource(R.drawable.advertise)
    }
}

package woowacourse.movie.presentation

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.model.AdModel

class AdViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val adImage = view.findViewById<ImageView>(R.id.imageMainAd)

    fun bind(ad: AdModel) {
        adImage.setImageResource(ad.adImage)
    }
}

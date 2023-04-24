package woowacourse.movie.presentation.main.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.main.MovieItem

class AdViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val adImage = view.findViewById<ImageView>(R.id.imageMainAd)

    fun bind(item: MovieItem.Ad) {
        adImage.setImageResource(item.adImage)
    }
}

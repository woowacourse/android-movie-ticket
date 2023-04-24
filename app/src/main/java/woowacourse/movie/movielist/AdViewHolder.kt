package woowacourse.movie.movielist

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.Ad
import woowacourse.movie.R

class AdViewHolder(private val view: View, private val listener: (Ad) -> Unit, ad: Ad) :
    RecyclerView.ViewHolder(view) {
    val adImage = itemView.findViewById<ImageView>(R.id.iv_ad)

    init {
        adImage.setOnClickListener {
            listener(ad)
        }
    }

    fun bind(item: Ad) {
        adImage.setImageResource(item.adImage)
    }
}

package woowacourse.movie.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.Ad
import woowacourse.movie.OnClickListener
import woowacourse.movie.R

class AdViewHolder(private val view: View, private val listener: OnClickListener<Ad>) :
    RecyclerView.ViewHolder(view) {
    val adImage = itemView.findViewById<ImageView>(R.id.iv_ad)

    fun bind(item: Ad) {
        adImage.setImageResource(item.adImage)
        adImage.setOnClickListener {
            listener.onClick(item)
        }
    }
}

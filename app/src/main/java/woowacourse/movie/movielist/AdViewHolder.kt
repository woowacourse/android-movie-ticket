package woowacourse.movie.movielist

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.dto.AdDto

class AdViewHolder(private val view: View, private val listener: OnClickListener<AdDto>) :
    RecyclerView.ViewHolder(view) {
    val adImg = itemView.findViewById<ImageView>(R.id.ad)

    fun bind(item: AdDto) {
        adImg.setImageResource(item.adImage)
        adImg.setOnClickListener {
            listener.onClick(item)
        }
    }
}

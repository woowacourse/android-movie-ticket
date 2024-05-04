package woowacourse.movie.presentation.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class AdViewHolder(
    itemView: View,
    private val bannerClickedListener: (Int) -> Unit,
) : RecyclerView.ViewHolder(itemView) {
    val adImage: ImageView = itemView.findViewById(R.id.adImage)

    init {
        adImage.setOnClickListener { bannerClickedListener(adapterPosition) }
    }

    fun bind(imageId: Int) {
        adImage.setImageResource(imageId)
    }
}

package woowacourse.movie.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.ui.model.AdModel

class AdViewHolder(view: View, private val onItemViewClick: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
    private val adImageView: ImageView by lazy { view.findViewById(R.id.advertise_image) }

    init {
        view.setOnClickListener { onItemViewClick(absoluteAdapterPosition) }
    }

    fun bind(ad: AdModel) {
        adImageView.setImageResource(ad.image)
    }
}

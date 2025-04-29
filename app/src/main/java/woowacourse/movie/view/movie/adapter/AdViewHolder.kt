package woowacourse.movie.view.movie.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class AdViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {
    private val adImageView: ImageView = view.findViewById(R.id.iv_advertisement)

    fun bind() {
        adImageView.setImageResource(R.drawable.advertisement)
    }
}

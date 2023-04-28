package woowacourse.movie.movieList.viewHolder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import model.ItemViewType
import woowacourse.movie.R

class AdViewHolder(
    val view: View,
    onAdClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(view) {
    private val imageView: ImageView = view.findViewById(R.id.ad_banner)

    init {
        imageView.setOnClickListener { onAdClick(adapterPosition) }
    }
    fun bind(
        movieAdItem: ItemViewType.AD,
    ) {
        imageView.setImageResource(movieAdItem.image)
    }
}

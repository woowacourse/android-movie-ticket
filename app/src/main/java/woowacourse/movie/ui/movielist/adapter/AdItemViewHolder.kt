package woowacourse.movie.ui.movielist.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieListModel

class AdItemViewHolder(
    view: View,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val banner = view.findViewById<ImageView>(R.id.item_banner)

    init {
        banner.setOnClickListener { onItemClick(adapterPosition) }
    }

    fun bind(ad: MovieListModel.AdModel) {
        banner.setImageResource(ad.banner)
    }
}

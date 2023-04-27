package woowacourse.movie.ui.movielist.adapter.viewholder

import android.view.View
import android.widget.ImageView
import woowacourse.movie.R
import woowacourse.movie.model.MovieListModel

class AdItemViewHolder(
    view: View,
    private val onItemClick: (Int) -> Unit,
) : ItemViewHolder(view) {

    private val banner = view.findViewById<ImageView>(R.id.item_banner)

    init {
        banner.setOnClickListener { onItemClick(adapterPosition) }
    }

    override fun bind(model: MovieListModel) {
        val ad = model as MovieListModel.AdModel
        banner.setImageResource(ad.banner)
    }
}

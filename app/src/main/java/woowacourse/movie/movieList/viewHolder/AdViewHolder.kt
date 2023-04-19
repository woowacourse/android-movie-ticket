package woowacourse.movie.movieList.viewHolder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import model.MovieAdItem
import woowacourse.movie.R

class AdViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val imageView: ImageView = view.findViewById(R.id.ad_banner)
    fun bind(
        movieAdItem: MovieAdItem,
        onAdClick: (MovieAdItem) -> Unit,
    ) {
        imageView.setImageResource(movieAdItem.image)
        imageView.setOnClickListener { onAdClick(movieAdItem) }
    }
}

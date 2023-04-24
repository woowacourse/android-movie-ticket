package woowacourse.movie.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieModelUi

class AdViewHolder(
    val view: View,
) : RecyclerView.ViewHolder(view) {

    private val movieImageView: ImageView = view.findViewById(R.id.ad_image_view)

    fun bind(item: MovieModelUi.AdUi) {
        movieImageView.setImageResource(item.adPoster)
    }
}

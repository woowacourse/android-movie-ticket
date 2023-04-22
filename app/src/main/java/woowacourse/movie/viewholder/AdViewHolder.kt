package woowacourse.movie.viewholder

import android.view.View
import android.widget.ImageView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieModelUi
class AdViewHolder(
    view: View,
) {
    private val adImageView = view.findViewById<ImageView>(R.id.ad_image_view)

    fun bind(adUi: MovieModelUi.AdUi) {
        adImageView.setImageResource(adUi.addPoster)
    }
}

package woowacourse.movie.movieList

import android.view.View
import android.widget.ImageView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieModelUi
class AdViewHolder(
    private val view: View,
) {
    fun bind(adUi: MovieModelUi.AdUi) {
        view.findViewById<ImageView>(R.id.ad_image_view).setImageResource(adUi.addPoster)
    }
}

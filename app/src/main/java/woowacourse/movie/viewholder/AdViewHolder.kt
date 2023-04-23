package woowacourse.movie.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieModelUi

class AdViewHolder(
    context: Context,
) : LinearLayout(context) {

    private val adImageView: ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.item_ad_list, this)
        adImageView = findViewById(R.id.ad_image_view)
    }

    fun bind(adUi: MovieModelUi.AdUi) {
        adImageView.setImageResource(adUi.addPoster)
    }
}

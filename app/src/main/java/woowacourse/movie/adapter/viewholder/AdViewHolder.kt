package woowacourse.movie.adapter.viewholder

import android.view.View
import android.widget.ImageView
import woowacourse.movie.R
import woowacourse.movie.model.MovieListItem

class AdViewHolder(view: View) : CustomViewHolder(view) {
    private val adImage: ImageView = view.findViewById(R.id.img_ad)

    override fun bind(data: MovieListItem) {
        data as MovieListItem.AdModel
        adImage.setImageResource(data.image)
    }
}

package woowacourse.movie.presentation.ui.screen.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Ads

class AdsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val poster: ImageView = view.findViewById(R.id.iv_ads)

    fun bind(ads: Ads) {
        poster.setImageResource(ads.poster)
    }
}

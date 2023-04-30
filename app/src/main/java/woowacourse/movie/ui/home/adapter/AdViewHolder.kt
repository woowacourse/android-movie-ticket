package woowacourse.movie.ui.home.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.MovieInfo

class AdViewHolder(
    view: View,
    private val onClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(view) {
    private val banner by lazy {
        view.findViewById<ImageView>(R.id.item_banner)
    }

    init {
        banner.setOnClickListener { onClick(adapterPosition) }
    }

    fun onBind(advertisement: MovieInfo.Advertisement) {
        banner.setImageResource(advertisement.adImage)
    }
}

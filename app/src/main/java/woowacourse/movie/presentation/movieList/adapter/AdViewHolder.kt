package woowacourse.movie.presentation.movieList.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class AdViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val ad = view.findViewById<ImageView>(R.id.iv_ad_content)

    fun bind() {
        ad.setImageResource(R.drawable.ad1)
    }
}

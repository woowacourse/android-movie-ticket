package woowacourse.movie.adapter

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

class MovieViewHolder(
    val image: ImageView,
    val title: TextView,
    val startDate: TextView,
    val endDate: TextView,
    val runningTime: TextView,
    val button: Button
) {
    companion object {
        val TAG_KEY = R.id.movie_view_holder_tag
    }
}
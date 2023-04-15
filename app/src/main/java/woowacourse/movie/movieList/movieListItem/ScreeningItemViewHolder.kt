package woowacourse.movie.movieList.movieListItem

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes

class ScreeningItemViewHolder(
    var posterView: ImageView,
    var titleView: TextView,
    var releaseDateView: TextView,
    var runningTimeView: TextView,
) {
    fun bind(
        @DrawableRes posterResource: Int,
        title: String,
        date: String,
        runningTime: String,
    ) {
        posterView.setImageResource(posterResource)
        titleView.text = title
        releaseDateView.text = date
        runningTimeView.text = runningTime
    }
}

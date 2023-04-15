package woowacourse.movie.movieList

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes

class MovieListViewHolder(
    var posterView: ImageView,
    var titleView: TextView,
    var releaseDateView: TextView,
    var runningTimeView: TextView,
    var reservationButton: Button,
) {
    fun bind(
        @DrawableRes posterResource: Int,
        title: String,
        date: String,
        runningTime: String,
        reservationListener: () -> Unit,
    ) {
        posterView.setImageResource(posterResource)
        titleView.text = title
        releaseDateView.text = date
        runningTimeView.text = runningTime
        reservationButton.setOnClickListener {
            reservationListener()
        }
    }
}

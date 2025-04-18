package woowacourse.movie

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MovieViewHolder(
    view: View,
) {
    val poster: ImageView = view.findViewById(R.id.poster)
    val title: TextView = view.findViewById(R.id.movie_title)
    val screeningDate: TextView = view.findViewById(R.id.screening_date)
    val runningTime: TextView = view.findViewById(R.id.running_time)
    val reserveButton: Button = view.findViewById(R.id.reserve_button)
}

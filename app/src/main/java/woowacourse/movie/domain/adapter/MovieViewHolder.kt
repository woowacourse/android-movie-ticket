package woowacourse.movie.domain.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

class MovieViewHolder(view: View) {
    val movieImage: ImageView = view.findViewById(R.id.movie_image)
    val movieTitle: TextView = view.findViewById(R.id.movie_title)
    val movieDate: TextView = view.findViewById(R.id.movie_date)
    val movieTime: TextView = view.findViewById(R.id.movie_time)
    val reserveButton: Button = view.findViewById(R.id.reserve_button)
}

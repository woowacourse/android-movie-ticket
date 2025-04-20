package woowacourse.movie.movielist

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

class ViewHolder(view: View) {
    val imageView: ImageView = view.findViewById(R.id.movie_image)
    val titleTextView: TextView = view.findViewById(R.id.movie_title)
    val dateTextView: TextView = view.findViewById(R.id.movie_date)
    val timeTextView: TextView = view.findViewById(R.id.movie_time)
    val reserveButton: Button = view.findViewById(R.id.reserve_button)
}

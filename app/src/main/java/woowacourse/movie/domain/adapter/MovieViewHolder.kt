package woowacourse.movie.domain.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

class MovieViewHolder(view: View) {
    val movieImage: ImageView = view.findViewById(R.id.iv_movie_image)
    val movieTitle: TextView = view.findViewById(R.id.tv_movie_title)
    val movieDate: TextView = view.findViewById(R.id.tv_movie_date)
    val movieTime: TextView = view.findViewById(R.id.tv_movie_time)
    val reserveButton: Button = view.findViewById(R.id.btn_reserve)
}

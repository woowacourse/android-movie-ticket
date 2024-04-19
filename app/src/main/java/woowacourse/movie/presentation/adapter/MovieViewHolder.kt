package woowacourse.movie.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

class MovieViewHolder(view: View) {
    val posterImage: ImageView = view.findViewById(R.id.posterImage)
    val title: TextView = view.findViewById(R.id.title)
    val screeningDate: TextView = view.findViewById(R.id.screeningDate)
    val runningTime: TextView = view.findViewById(R.id.runningTime)
    val reserveButton: TextView = view.findViewById(R.id.reserveButton)
}

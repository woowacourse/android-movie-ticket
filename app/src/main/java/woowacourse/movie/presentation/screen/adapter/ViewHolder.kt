package woowacourse.movie.presentation.screen.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

class ViewHolder(val view: View) {
    val title: TextView = view.findViewById(R.id.title)
    val screenDate: TextView = view.findViewById(R.id.screen_date)
    val runningTime: TextView = view.findViewById(R.id.running_time)
    val image: ImageView = view.findViewById(R.id.poster_image)
    val reservationButton: Button = view.findViewById(R.id.reservation_button)
}

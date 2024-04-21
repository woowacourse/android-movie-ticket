package woowacourse.movie.ui.main.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

class MovieViewHolder(view: View) {
    val posterImage: ImageView = view.findViewById(R.id.poster_image)
    val titleText: TextView = view.findViewById(R.id.title_text)
    val screeningDateText: TextView = view.findViewById(R.id.screening_date_text)
    val runningTimeText: TextView = view.findViewById(R.id.running_time_text)
    val reservationButton: Button = view.findViewById(R.id.reservation_button)
}

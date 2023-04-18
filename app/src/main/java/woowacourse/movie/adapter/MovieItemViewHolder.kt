package woowacourse.movie.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

data class MovieItemViewHolder(
    val itemView: View
) {
    val movieNameTextView: TextView =
        itemView.findViewById(R.id.movie_name_text_view)
    val movieImageView: ImageView =
        itemView.findViewById(R.id.movie_image_view)
    val screeningDateTextView: TextView =
        itemView.findViewById(R.id.movie_screening_date_text_view)
    val movieRunningTimeTextView: TextView =
        itemView.findViewById(R.id.movie_running_time_text_view)
    val reservationButton: Button =
        itemView.findViewById(R.id.reservation_button)
}

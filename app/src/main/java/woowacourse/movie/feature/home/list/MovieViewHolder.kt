package woowacourse.movie.feature.home.list

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.feature.home.ui.MovieUiModel

class MovieViewHolder(
    itemView: View,
    reservationButtonClickListener: ReservationButtonClickListener,
) : RecyclerView.ViewHolder(itemView) {
    private val posterImage: ImageView by lazy { itemView.findViewById(R.id.poster_image) }
    private val titleText: TextView by lazy { itemView.findViewById(R.id.title_text) }
    private val screeningDateText: TextView by lazy { itemView.findViewById(R.id.screening_date_text) }
    private val runningTimeText: TextView by lazy { itemView.findViewById(R.id.running_time_text) }
    private val reservationButton: Button by lazy { itemView.findViewById(R.id.reservation_button) }

    init {
        reservationButton.setOnClickListener {
            reservationButtonClickListener.onClick(it, bindingAdapterPosition)
        }
    }

    fun bind(movie: MovieUiModel) {
        with(movie) {
            posterImage.setImageDrawable(posterImageDrawable)
            titleText.text = titleMessage
            screeningDateText.text = screeningDateMessage
            runningTimeText.text = runningTimeMessage
        }
    }
}

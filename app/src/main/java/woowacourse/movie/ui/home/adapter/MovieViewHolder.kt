package woowacourse.movie.ui.home.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.ui.ReservationButtonClickListener
import woowacourse.movie.ui.utils.getImageFromId
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieViewHolder(
    private val view: View,
    private val reservationButtonClickListener: ReservationButtonClickListener,
) :
    RecyclerView.ViewHolder(view) {
    private val posterImage: ImageView = view.findViewById(R.id.poster_image)
    private val titleText: TextView = view.findViewById(R.id.title_text)
    private val screeningDateText: TextView = view.findViewById(R.id.screening_date_text)
    private val runningTimeText: TextView = view.findViewById(R.id.running_time_text)
    private val reservationButton: Button = view.findViewById(R.id.reservation_button)

    fun bind(movieContent: MovieContent) {
        with(movieContent) {
            val image = imageId.getImageFromId(view.context)
            posterImage.setImageResource(image)
            titleText.text = title
            screeningDateText.text =
                view.context.resources
                    .getString(R.string.screening_date)
                    .format(dateFormatter(openingMovieDate), dateFormatter(endingMoviesDate))
            runningTimeText.text =
                view.context.resources.getString(R.string.running_time).format(runningTime)
            reservationButton.setOnClickListener {
                reservationButtonClickListener.onClick(it, id)
            }
        }
    }

    private fun dateFormatter(movieDate: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        return movieDate.format(formatter)
    }
}

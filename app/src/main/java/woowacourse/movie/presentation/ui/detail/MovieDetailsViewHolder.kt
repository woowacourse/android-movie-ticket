package woowacourse.movie.presentation.ui.detail

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

class MovieDetailsViewHolder(private val view: View) {
    private val posterImage: ImageView = view.findViewById(R.id.posterImage)
    private val titleTextView: TextView = view.findViewById(R.id.title)
    private val screeningDateTextView: TextView = view.findViewById(R.id.screeningDate)
    private val runningTimeTextView: TextView = view.findViewById(R.id.runningTime)
    private val summaryTextView: TextView = view.findViewById(R.id.summary)
    private val reservationCountTextView: TextView = view.findViewById(R.id.reservationCount)
    val minusButton: Button = view.findViewById(R.id.minusButton)
    val plusButton: Button = view.findViewById(R.id.plusButton)
    val reserveButton: Button = view.findViewById(R.id.reserveButton)

    fun bindDetails(
        posterImageId: Int,
        title: String,
        screeningDate: String,
        runningTime: Int,
        summary: String,
    ) {
        posterImage.setImageResource(posterImageId)
        titleTextView.text = title
        screeningDateTextView.text = view.context.getString(R.string.screening_date_format, screeningDate)
        runningTimeTextView.text = view.context.getString(R.string.running_time_format, runningTime)
        summaryTextView.text = summary
    }

    fun updateReservationCount(count: Int) {
        reservationCountTextView.text = count.toString()
    }
}

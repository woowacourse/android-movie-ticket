package woowacourse.movie.presentation.ui.detail

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.presentation.uimodel.MovieUiModel
import woowacourse.movie.presentation.utils.toDrawableIdByName

class MovieDetailsViewHolder(private val view: View) {
    private val posterImage: ImageView = view.findViewById(R.id.posterImage)
    private val titleTextView: TextView = view.findViewById(R.id.title)
    private val screeningDateTextView: TextView = view.findViewById(R.id.screeningDate)
    private val runningTimeTextView: TextView = view.findViewById(R.id.runningTime)
    private val summaryTextView: TextView = view.findViewById(R.id.summary)
    val reservationCountTextView: TextView = view.findViewById(R.id.reservationCount)
    val minusButton: Button = view.findViewById(R.id.minusButton)
    val plusButton: Button = view.findViewById(R.id.plusButton)
    val reserveButton: Button = view.findViewById(R.id.reserveButton)

    fun bindDetails(movieUiModel: MovieUiModel) {
        val imageResource = movieUiModel.posterName.toDrawableIdByName(view.context)
        imageResource?.let { posterImage.setImageResource(it) }
        titleTextView.text = movieUiModel.title
        screeningDateTextView.text =
            view.context.getString(R.string.screening_date_format, movieUiModel.screeningDate)
        runningTimeTextView.text = view.context.getString(R.string.running_time_format, movieUiModel.runningTime)
        summaryTextView.text = movieUiModel.summary
    }

    fun updateReservationCount(count: Int) {
        reservationCountTextView.text = count.toString()
    }
}
